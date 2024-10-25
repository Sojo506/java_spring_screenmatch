package com.oracleone.screenmatch.controller;

import com.oracleone.screenmatch.dto.SeriesDTO;
import com.oracleone.screenmatch.service.SeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SeriesController {
    @Autowired
    private SeriesService service;

    @GetMapping("/series")
    public List<SeriesDTO> getAllSeries() {
        return service.getAllSeries();
    }
}
