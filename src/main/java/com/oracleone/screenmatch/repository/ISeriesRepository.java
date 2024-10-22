package com.oracleone.screenmatch.repository;

import com.oracleone.screenmatch.model.Category;
import com.oracleone.screenmatch.model.Series;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ISeriesRepository extends JpaRepository<Series, Long> {
    Optional<Series> findByTitleContainsIgnoreCase(String seriesName);

    List<Series> findTop5ByOrderByRatingDesc();

    List<Series> findByGenre(Category category);

    List<Series> findBySeasonsLessThanEqualAndRatingGreaterThanEqual
            (Integer seasons, Double rating);
}
