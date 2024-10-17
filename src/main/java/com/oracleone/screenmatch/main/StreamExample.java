package com.oracleone.screenmatch.main;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamExample {

    public void showExample() {
        List<String> names = Arrays.asList("Sojo", "Elon Musk", "Steve Jobs",
                "Einstein", "Sócrates", "Platón");
        List<Integer> numbers = Arrays.asList(5, 8, 9, 7, 1, 21, 19);
        System.out.println("numbers = " + numbers);

       /* names.stream()
                .sorted()
                .map(n -> n.toLowerCase())
                .forEach(n -> System.out.println(n));*/
    }
}
