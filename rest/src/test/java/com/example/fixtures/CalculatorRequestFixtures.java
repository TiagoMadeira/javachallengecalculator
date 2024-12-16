package com.example.fixtures;

import com.example.domain.CalculatorRequest;

import java.math.BigDecimal;

public class CalculatorRequestFixtures {
    private static final CalculatorRequest SUM = new CalculatorRequest(BigDecimal.TWO, BigDecimal.TWO, "sum", 2);
    private static final CalculatorRequest SUBTRACTION = new CalculatorRequest(BigDecimal.TWO, BigDecimal.TWO, "subtraction", 2);
    private static final CalculatorRequest MULTIPLICATION = new CalculatorRequest(BigDecimal.TWO, BigDecimal.TWO, "multiplication", 2);
    private static final CalculatorRequest DIVISION = new CalculatorRequest(BigDecimal.TWO, BigDecimal.TWO, "division", 2);
    private static final CalculatorRequest FAULTYDIVISION = new CalculatorRequest(BigDecimal.TWO, BigDecimal.ZERO, "division", 2);

    public static CalculatorRequest sum() {
        return SUM;
    }

    public static CalculatorRequest subtraction() {
        return SUBTRACTION;
    }

    public static CalculatorRequest multiplication() {
        return MULTIPLICATION;
    }

    public static CalculatorRequest division() {
        return DIVISION;
    }
    public static CalculatorRequest faultyDivision() {
        return FAULTYDIVISION;
    }

}
