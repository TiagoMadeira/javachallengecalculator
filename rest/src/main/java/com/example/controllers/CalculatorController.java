package com.example.controllers;



import com.example.CalculatorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class CalculatorController {

    private final CalculatorService calculatorService;

    public CalculatorController(CalculatorService calculatorService){
        this.calculatorService = calculatorService;
    }

    @GetMapping("sum")
    public float sum(@RequestParam Float x, @RequestParam Float y ){
        return calculatorService.sum(x, y);
    }

    @GetMapping("subtraction")
    public float subtraction(@RequestParam Float x, @RequestParam Float y ){
        return calculatorService.subtraction(x, y);
    }

    @GetMapping("multiplication")
    public float multiplication(@RequestParam Float x, @RequestParam Float y ){
        return calculatorService.multiplication(x, y);
    }

    @GetMapping("division")
    public float division(@RequestParam Float x, @RequestParam Float y){
        return calculatorService.division(x, y);
    }
}
