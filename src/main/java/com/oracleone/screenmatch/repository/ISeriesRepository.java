package com.oracleone.screenmatch.repository;

import com.oracleone.screenmatch.model.Category;
import com.oracleone.screenmatch.model.Episode;
import com.oracleone.screenmatch.model.Series;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ISeriesRepository extends JpaRepository<Series, Long> {
    Optional<Series> findByTitleContainsIgnoreCase(String seriesName);

    List<Series> findTop5ByOrderByRatingDesc();

    List<Series> findByGenre(Category category);

    List<Series> findBySeasonsLessThanEqualAndRatingGreaterThanEqual
            (Integer seasons, Double rating);

    @Query("SELECT s FROM Series s WHERE s.seasons <= :seasons AND s.rating >= :rating")
    List<Series> seriesBySeasonAndRating(Integer seasons, Double rating);

    @Query("SELECT e FROM Series s JOIN s.episodes e WHERE e.title ILIKE %:episodeName%")
    List<Episode> episodesByName(String episodeName);

}
