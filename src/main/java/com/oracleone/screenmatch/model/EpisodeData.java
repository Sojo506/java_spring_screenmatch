package com.oracleone.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// This ignores properties that we aren't mapping
@JsonIgnoreProperties(ignoreUnknown = true)
public record EpisodeData(
        @JsonAlias("Title") String title,
        @JsonAlias("Episode") Integer episodeNumber,
        @JsonAlias("imdbRating") String rating,
        @JsonAlias("Released") String releaseDate
) {
}
