package com.oracleone.screenmatch.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SeriesController {

    @GetMapping("/series")
    public String showMessage() {
        return "Hello motha foca";
    }
}
