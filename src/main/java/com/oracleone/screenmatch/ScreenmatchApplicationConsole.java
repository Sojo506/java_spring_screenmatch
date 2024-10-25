/*
package com.oracleone.screenmatch;


import com.oracleone.screenmatch.main.Main;
import com.oracleone.screenmatch.repository.ISeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenmatchApplicationConsole implements CommandLineRunner {

    @Autowired
    private ISeriesRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(ScreenmatchApplicationConsole.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Main main = new Main(repository);
        main.start();
    }
}*/
