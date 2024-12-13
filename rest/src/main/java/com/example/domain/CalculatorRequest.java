package com.example.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;


@Getter
public class CalculatorRequest {

    private final Float x;
    private final Float y;
    private final String operation;

    @JsonCreator
    public CalculatorRequest(@JsonProperty("x")Float x,
                             @JsonProperty("y")Float y,
                             @JsonProperty("operation")String operation) {
        this.x = x;
        this.y = y;
        this.operation = operation;
    }
}
