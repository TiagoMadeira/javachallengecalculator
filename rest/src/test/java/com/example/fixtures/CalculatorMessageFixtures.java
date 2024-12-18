package com.example.fixtures;

import com.example.CalculatorMessage;

import java.math.BigDecimal;

public class CalculatorMessageFixtures {
    private static final CalculatorMessage SUM = new CalculatorMessage(BigDecimal.TWO, BigDecimal.TWO, "sum", 2);
    private static final CalculatorMessage SUBTRACTION = new CalculatorMessage(BigDecimal.TWO, BigDecimal.TWO, "subtraction", 2);
    private static final CalculatorMessage MULTIPLICATION = new CalculatorMessage(BigDecimal.TWO, BigDecimal.TWO, "multiplication", 2);
    private static final CalculatorMessage DIVISION = new CalculatorMessage(BigDecimal.TWO, BigDecimal.TWO, "division", 2);
    private static final CalculatorMessage FAULTYDIVISION = new CalculatorMessage(BigDecimal.TWO, BigDecimal.ZERO, "division", 2);

    public static CalculatorMessage sum() {
        return SUM;
    }
    public static CalculatorMessage subtraction() {
        return SUBTRACTION;
    }
    public static CalculatorMessage multiplication() {
        return MULTIPLICATION;
    }
    public static CalculatorMessage division() {
        return DIVISION;
    }
    public static CalculatorMessage faultyDivision() {
        return FAULTYDIVISION;
    }

}
