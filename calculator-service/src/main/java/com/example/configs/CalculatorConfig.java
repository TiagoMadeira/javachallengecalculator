package com.example.configs;

public class CalculatorConfig {

    private final int precision;

    public CalculatorConfig(int precision){
        this.precision = precision;
    }

    public int getConfigurePrecision(){
        return precision;
    }


}
