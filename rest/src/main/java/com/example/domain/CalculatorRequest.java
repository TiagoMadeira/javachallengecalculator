package com.example.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;


@Getter
public class CalculatorRequest {

    private final Float x;
    private final Float y;
    private final String operation;
    @Setter
    @JsonInclude
    private String result;

    @JsonCreator
    public CalculatorRequest(@JsonProperty("x")Float x,
                             @JsonProperty("y")Float y,
                             @JsonProperty("operation")String operation
                            ) {
        this.x = x;
        this.y = y;
        this.operation = operation;
        this.result = "result not set";
    }

}
