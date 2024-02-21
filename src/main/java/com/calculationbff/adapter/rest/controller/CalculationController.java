package com.calculationbff.adapter.rest.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculationController {

    @GetMapping("/")
    public String calcExpose(){
        return "its working";
    }
}
