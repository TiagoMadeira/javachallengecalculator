package com.example.services.impl;

import com.example.CalculatorMessage;
import com.example.config.KafkaConfigProps;
import com.example.services.CalculatorRequestReplyService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.MDC;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.requestreply.RequestReplyFuture;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;


import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.concurrent.ExecutionException;

@Service
public class KafkaCalculatorRequestService implements CalculatorRequestReplyService {

    private static Logger logger = LogManager.getLogger(KafkaCalculatorRequestService.class);

    private final ReplyingKafkaTemplate<String, CalculatorMessage, CalculatorMessage> replyingKafkaTemplate;
    private final KafkaConfigProps kafkaConfigProps;

    public KafkaCalculatorRequestService(
            final ReplyingKafkaTemplate<String, CalculatorMessage, CalculatorMessage> replyingKafkaTemplate, KafkaConfigProps kafkaConfigProps) {

        this.replyingKafkaTemplate = replyingKafkaTemplate;
        this.kafkaConfigProps = kafkaConfigProps;
    }

    @Override
    public CalculatorMessage calculatorRequestReply(final CalculatorMessage calculatorMessage ) throws InterruptedException, ExecutionException {

        //Create producer record
        ProducerRecord<String, CalculatorMessage> record = new ProducerRecord<>(kafkaConfigProps.getTopic(), calculatorMessage);

        //Propagate the MDC
        record.headers().add("Request.id", MDC.get("Request.id").getBytes());


        //Send the request
        RequestReplyFuture<String, CalculatorMessage, CalculatorMessage> replyFuture = replyingKafkaTemplate.sendAndReceive(record, Duration.ofSeconds(kafkaConfigProps.getRequestTimeout()));

        //Get sent Producer Record for Logging
        SendResult<String, CalculatorMessage> sendResult = replyFuture.getSendFuture().get();
        logKafkaRequest(sendResult.getProducerRecord().value());

        //Get consumer record listener record

        ConsumerRecord<String, CalculatorMessage> consumerRecord = replyFuture.get();
        logKafkaReply(consumerRecord.value());
        return consumerRecord.value();
    }



    private void logKafkaRequest(CalculatorMessage request){

        MDC.put("Kafka.Request.Operation",request.getOperation());
        MDC.put("Kafka.Request.xOperand", request.getX().toString());
        MDC.put("Kafka.Request.yOperand",request.getY().toString());
        MDC.put("Kafka.Request.Precision", String.valueOf(request.getPrecision()));
        logger.info("[Rest][Kafka][Output] Kafka request sent!");
    }

    private void logKafkaReply(CalculatorMessage reply){
        // Just logging the result to avoid clutter
        MDC.put("Kafka.Reply.Result", String.valueOf(reply.getResult()));
        logger.info("[Rest][Kafka][Input] Kafka reply received!");
    }
}
