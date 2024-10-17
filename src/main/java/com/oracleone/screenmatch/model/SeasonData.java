package com.oracleone.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

// This ignores properties that we aren't mapping
@JsonIgnoreProperties(ignoreUnknown = true)
public record SeasonData(
        @JsonAlias("Season") Integer seasonNumber,
        @JsonAlias("Episodes") List<EpisodeData> episodes

) {
}
