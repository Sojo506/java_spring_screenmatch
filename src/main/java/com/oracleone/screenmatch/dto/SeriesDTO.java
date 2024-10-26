package com.oracleone.screenmatch.dto;

import com.oracleone.screenmatch.model.Category;

public record SeriesDTO(
        Long id,
        String title,
        Category genre,
        String actors,
        String plot,
        String poster,
        Integer seasons,
        Double rating
) {
}
