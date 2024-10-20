package com.oracleone.screenmatch.repository;

import com.oracleone.screenmatch.model.Series;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISeriesRepository extends JpaRepository<Series, Long> {
}
