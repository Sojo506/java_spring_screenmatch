package com.oracleone.screenmatch;

import com.oracleone.screenmatch.main.Main;
import com.oracleone.screenmatch.main.StreamExample;
import com.oracleone.screenmatch.model.SeasonData;
import com.oracleone.screenmatch.model.SeriesData;
import com.oracleone.screenmatch.service.ConvertData;
import com.oracleone.screenmatch.service.FetchApi;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ScreenmatchApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Main main = new Main();
        main.start();

        /*StreamExample streamExample = new StreamExample();
        streamExample.showExample();*/
    }
}
