package com.example.services.impl;

import com.example.domain.CalculatorRequest;
import com.example.services.CalculatorRequestReplyService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.requestreply.RequestReplyFuture;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.Duration;
import java.util.concurrent.ExecutionException;

@Service
public class KafkaCalculatorRequestService implements CalculatorRequestReplyService {

    private final ReplyingKafkaTemplate<String, CalculatorRequest, CalculatorRequest> replyingKafkaTemplate;

    public KafkaCalculatorRequestService(
            final ReplyingKafkaTemplate<String, CalculatorRequest, CalculatorRequest> replyingKafkaTemplate) {

        this.replyingKafkaTemplate = replyingKafkaTemplate;
    }

    @Override
    public CalculatorRequest calculatorRequestReply(final CalculatorRequest calculatorRequest) throws InterruptedException, ExecutionException {
        ProducerRecord<String, CalculatorRequest> record = new ProducerRecord<>("calculator.requests", calculatorRequest);


            RequestReplyFuture<String, CalculatorRequest, CalculatorRequest> replyFuture = replyingKafkaTemplate.sendAndReceive(record, Duration.ofSeconds(10));

            //Get sent Producer Record for Logging
            SendResult<String, CalculatorRequest> sendResult = replyFuture.getSendFuture().get();

            //Get consumer record listener record
            ConsumerRecord<String, CalculatorRequest> consumerRecord = replyFuture.get();

        return consumerRecord.value();
    }
}
