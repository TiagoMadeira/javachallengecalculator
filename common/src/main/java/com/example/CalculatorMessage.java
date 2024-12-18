package com.example;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
public class CalculatorMessage {

    private final BigDecimal x;
    private final BigDecimal y;
    private final String operation;
    private final int precision;
    @Setter
    @JsonInclude
    private String result;

    @JsonCreator
    public CalculatorMessage(@JsonProperty("x")BigDecimal x,
                             @JsonProperty("y")BigDecimal y,
                             @JsonProperty("operation")String operation,
                             @JsonProperty("precision")int precision
    ) {
        this.x = x;
        this.y = y;
        this.operation = operation;
        this.precision = precision;
        this.result = "result not set";
    }

}
