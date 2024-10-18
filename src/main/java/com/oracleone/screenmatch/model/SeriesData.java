package com.oracleone.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// This ignores properties that we aren't mapping
@JsonIgnoreProperties(ignoreUnknown = true)
public record SeriesData(
        @JsonAlias("Title") String title,
        @JsonAlias("Genre") String genre,
        @JsonAlias("Actors") String actors,
        @JsonAlias("Plot") String plot,
        @JsonAlias("Poster") String poster,
        @JsonAlias("totalSeasons") Integer seasons,
        @JsonAlias("imdbRating") double rating
    ) {
}
