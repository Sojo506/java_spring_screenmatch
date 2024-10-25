package com.oracleone.screenmatch.service;

import com.oracleone.screenmatch.dto.SeriesDTO;
import com.oracleone.screenmatch.repository.ISeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeriesService {
    @Autowired
    private ISeriesRepository repository;

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
