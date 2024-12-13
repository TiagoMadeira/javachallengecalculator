package com.example.services.impl;

import com.example.domain.CalculatorRequest;
import com.example.services.CalculatorRequestReplyService;
import com.fasterxml.jackson.core.JsonProcessingException;
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


    private final ReplyingKafkaTemplate<String, Object, Object> replyingKafkaTemplate;


    public KafkaCalculatorRequestService(
            final ReplyingKafkaTemplate<String, Object, Object> replyingKafkaTemplate) {

        this.replyingKafkaTemplate = replyingKafkaTemplate;
    }

    @Override
    public ProducerRecord<String, Object> calculatorRequestReply(final CalculatorRequest CalculatorRequest) throws InterruptedException, ExecutionException, JsonProcessingException {
        ProducerRecord<String, Object> record = new ProducerRecord<>("calculator.requests", CalculatorRequest);
        RequestReplyFuture<String, Object, Object> replyFuture  = replyingKafkaTemplate.sendAndReceive(record, Duration.ofSeconds(1));
        SendResult<String, Object> sendResult = replyFuture.getSendFuture().get();

        return sendResult.getProducerRecord();
    }
}
