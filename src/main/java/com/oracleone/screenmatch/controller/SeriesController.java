package com.oracleone.screenmatch.controller;

import com.oracleone.screenmatch.dto.SeriesDTO;
import com.oracleone.screenmatch.service.SeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/series")
public class SeriesController {
    @Autowired
    private SeriesService service;


    @GetMapping("")
    public List<SeriesDTO> getAllSeries() {
        return service.getAllSeries();
    }

    @GetMapping("/top5")
    public List<SeriesDTO> getTop5() {
        return service.getTop5();
    }

    @GetMapping("/releases")
    public List<SeriesDTO> getRecentReleases() {
        return service.getRecentReleases();
    }
}
