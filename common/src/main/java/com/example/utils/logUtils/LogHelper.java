package com.example.utils.logUtils;

import com.example.CalculatorMessage;
import org.slf4j.MDC;

public class LogCalculatorMessage {


    public void logToMDC(CalculatorMessage alculatorMessage){


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

    private void logRequest(CalculatorMessage request, String id) {
        MDC.put("Request.id", id);
        MDC.put("Kafka.Request.Operation", request.getOperation());
        MDC.put("Kafka.Request.xOperand", request.getX().toString());
        MDC.put("Kafka.Request.yOperand", request.getY().toString());
        MDC.put("Kafka.Request.Precision", String.valueOf(request.getPrecision()));
        logger.info("[calculator-service][Kafka][Input] Kafka request receive!");
    }

    private void logReply(CalculatorMessage request) {
        MDC.put("Kafka.reply.result", request.getOperation());
        logger.info("[calculator-service][Kafka][Output] Kafka reply sent!");
    } 
}
