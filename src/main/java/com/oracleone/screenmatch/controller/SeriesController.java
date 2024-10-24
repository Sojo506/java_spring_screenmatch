package com.oracleone.screenmatch.controller;

import com.oracleone.screenmatch.dto.SeriesDTO;
import com.oracleone.screenmatch.model.Series;
import com.oracleone.screenmatch.repository.ISeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class SeriesController {
    @Autowired
    private ISeriesRepository repository;

    @GetMapping("/series")
    public List<SeriesDTO> getAllSeries() {
        return (List<SeriesDTO>) repository.findAll().stream()
                .map(s -> new SeriesDTO(
                        s.getTitle(),
                        s.getGenre(),
                        s.getActors(),
                        s.getPlot(),
                        s.getPoster(),
                        s.getSeasons(),
                        s.getRating()
                ))
                .collect(Collectors.toList());
    }
}
