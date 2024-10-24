package com.oracleone.screenmatch;

import com.oracleone.screenmatch.main.Main;
import com.oracleone.screenmatch.repository.ISeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenmatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScreenmatchApplication.class, args);
    }


}
