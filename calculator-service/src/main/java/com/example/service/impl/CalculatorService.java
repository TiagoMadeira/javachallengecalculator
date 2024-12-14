package com.example.service.impl;


import com.example.configs.CalculatorConfig;
import com.example.service.CalculatorInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

@Service
public class CalculatorService implements CalculatorInterface {

    private final CalculatorConfig calculatorConfigs;

    @Autowired
    public CalculatorService(CalculatorConfig calculatorConfigs){
        this.calculatorConfigs = calculatorConfigs;
    }

    @Override
    public BigDecimal sum(BigDecimal x, BigDecimal y, int precision) {

        return x.add(y,setupMathContext( precision)) ;
    }

    @Override
    public BigDecimal subtraction(BigDecimal x, BigDecimal y, int precision) {

        return x.subtract(y, setupMathContext(precision));
    }

    @Override
    public BigDecimal multiplication(BigDecimal x, BigDecimal y,int precision) {

        return x.multiply(y , setupMathContext(precision));
    }

    @Override
    public BigDecimal division(BigDecimal x, BigDecimal y,int precision) throws ArithmeticException {
        return x.divide(y, setupMathContext(precision));
    }

    private MathContext setupMathContext(int precision){
        return (precision == 0) ? new MathContext(calculatorConfigs.getDefaultPrecision(), RoundingMode.HALF_UP):
                new MathContext(precision, RoundingMode.HALF_UP);
    }
}
