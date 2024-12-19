package com.example.utils.logUtils;

import com.example.CalculatorMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.MDC;

public class LogHelper {

    private static Logger logger = LogManager.getLogger("lognow");

    public void loadAndLogCalculatorMessageToMDC(CalculatorMessage calculatorMessage, String Message){
        MDC.put("Kafka.CMessage.Operation",calculatorMessage.getOperation());
        MDC.put("Kafka.CMessage.xOperand", calculatorMessage.getX().toString());
        MDC.put("Kafka.CMessage.yOperand",calculatorMessage.getY().toString());
        MDC.put("Kafka.CMessage.Precision", String.valueOf(calculatorMessage.getPrecision()));
        MDC.put("Kafka.CMessage.Result", String.valueOf(calculatorMessage.getResult()));
        logger.info(Message);
    }
}
