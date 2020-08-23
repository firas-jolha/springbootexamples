package com.jolhagroup.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {

    @GetMapping("/error")
    public String handleError(){
        return "An error has been occurred";
    }
}
