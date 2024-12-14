package com.example.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public interface CalculatorInterface {

    BigDecimal sum(BigDecimal x, BigDecimal y,int precision);
    BigDecimal subtraction(BigDecimal x, BigDecimal y, int precision);
    BigDecimal multiplication(BigDecimal x, BigDecimal y, int precision);
    BigDecimal division(BigDecimal x, BigDecimal y, int precision);
}
