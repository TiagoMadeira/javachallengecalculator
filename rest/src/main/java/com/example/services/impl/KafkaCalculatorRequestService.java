package com.example.services.impl;

import com.example.CalculatorMessage;
import com.example.config.KafkaConfigProps;
import com.example.services.CalculatorRequestReplyService;
import com.example.utils.logUtils.LogHelper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.logging.log4j.Logger;
import org.slf4j.MDC;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.requestreply.RequestReplyFuture;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;


import java.time.Duration;
import java.util.concurrent.ExecutionException;

@Service
public class KafkaCalculatorRequestService implements CalculatorRequestReplyService {

    private static final LogHelper loggerHelper = new LogHelper();
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
        loggerHelper.loadAndLogCalculatorMessageToMDC(sendResult.getProducerRecord().value(),
                "[Rest][Kafka][Output] Kafka request sent!");

        //Get consumer record listener record
        ConsumerRecord<String, CalculatorMessage> consumerRecord = replyFuture.get();
        loggerHelper.loadAndLogCalculatorMessageToMDC(consumerRecord.value(),
                "[Rest][Kafka][Input] Kafka reply received!");

        return consumerRecord.value();
    }


}
