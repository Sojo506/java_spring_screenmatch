package com.oracleone.screenmatch.main;

import com.oracleone.screenmatch.model.SeasonData;
import com.oracleone.screenmatch.model.SeriesData;
import com.oracleone.screenmatch.service.ConvertData;
import com.oracleone.screenmatch.service.FetchApi;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private final String API_KEY_OMDB = "&apikey=6ee384bf";
    private final String URL = "https://www.omdbapi.com/?t=";
    private final Scanner sc = new Scanner(System.in);
    private final FetchApi fetchApi = new FetchApi();
    private final ConvertData convertData = new ConvertData();

    public void start() {
        var opt = -1;
        while (opt != 0) {
            var menu = """
                    \n>===Select an option===<
                    
                    1 - Search series
                    2 - Search episodes
                    
                    0 - Exit
                    >===                  ===<
                    """;


            System.out.println(menu);
            opt = sc.nextInt();
            sc.nextLine();

            switch (opt) {
                case 1:
                    break;
                case 2:
                    searchEpisodesBySeries();
                    break;
                case 0:
                    System.out.println("Closing the app...");
                    break;
                default:
                    System.out.println("Invalid option");
            }

        }

    }

    private SeriesData getSeriesData() {
        // gets series data
        System.out.print("Enter series name you want to search for: ");
        var seriesName = sc.nextLine().replace(" ", "+");

        var json = fetchApi.getData(
                URL +
                        seriesName +
                        API_KEY_OMDB);

        return convertData.getData(json, SeriesData.class);
    }

    private void searchEpisodesBySeries() {
        SeriesData seriesData = getSeriesData();
        List<SeasonData> seasons = new ArrayList<>();

        // gets season data
        try {
            for (int i = 1; i <= seriesData.seasons(); i++) {
                var json = fetchApi.getData(
                        URL +
                                seriesData.title().replace(" ", "+") +
                                "&season=" +
                                i +
                                API_KEY_OMDB);

                var seasonData = convertData.getData(json, SeasonData.class);
                seasons.add(seasonData);
            }
        } catch (NullPointerException e) {
            System.out.println("Series not found");
        }

        seasons.forEach(System.out::println);
    }


}


//seasons.forEach(System.out::println);
//        seasons.forEach(s -> System.out.println(s));

//        for (int i = 0; i < seriesData.seasons(); i++) {
//            List<EpisodeData> episodeDataList = seasons.get(i).episodes();
//            System.out.println("Season " + (i + 1));
//
//            for (int j = 0; j < episodeDataList.size(); j++) {
//                System.out.println(j + 1 + ". " + episodeDataList.get(j).title());
//            }
//        }

// show episode's title for each season
// seasons.forEach(s -> s.episodes().forEach(e -> System.out.println(e.title())));

        /*List<EpisodeData> episodeDataList = seasons.stream()
                .flatMap(s -> s.episodes().stream())
                .collect(Collectors.toList());*/

        /*episodeDataList.stream()
                .filter(e -> !e.rating().equals("N/A"))
                .sorted(Comparator.comparing(EpisodeData::rating).reversed())
                .limit(5)
                .forEach(e -> System.out.println(e));*/


// gets the list of episodes of each season
        /*List<Episode> episodeList = seasons.stream()
                .flatMap(s -> s.episodes().stream()
                        .map(e -> new Episode(s.seasonNumber(), e)))
                .collect(Collectors.toList());*/

// episodeList.forEach(e -> System.out.println(e));

// search episodes by year
        /*System.out.print("Enter the year you'd like to search for: ");
        var year = sc.nextInt();
        sc.nextLine();

        LocalDate searchDate = LocalDate.of(year, 1, 1);

        DateTimeFormatter dts = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        episodeList.stream()
                .filter(e -> e.getReleaseDate() != null && e.getReleaseDate().isAfter(searchDate))
                .forEach(e -> System.out.println(
                        "**Season: " + e.getSeasonNumber() +
                                "\nEpisode: " + e.getTitle() +
                                "\nRelease date: " + e.getReleaseDate().format(dts) + "**"
                ));*/

// search episodes by a fragment of text
        /*System.out.print("Enter the episode's title you want to search for: ");
        var fragmentOfText = sc.nextLine().toLowerCase();

        Optional<Episode> firstSearch = episodeList.parallelStream()
                .filter(e -> e.getTitle().toLowerCase().contains(fragmentOfText))
                .peek(e -> System.out.println("Filter: " + e))
                .sorted((a, b) -> {
                    if (a.getSeasonNumber() < b.getSeasonNumber()) {
                        return -1;
                    } else return 1;
                })
                .peek(e -> System.out.println("Sort: " + e))
                .findAny();

        if (firstSearch.isPresent()) {
            System.out.println("Episode found!");
            System.out.println("Data: " + firstSearch.get());
        } else {
            System.out.println("Not found");
        }*/

        /*Map<Integer, Double> ratingsBySeason = episodeList.stream()
                .filter(e -> e.getRating() > 0.0)
                .collect(Collectors.groupingBy(
                        Episode::getSeasonNumber,
                        Collectors.averagingDouble(Episode::getRating)
                ));

        System.out.println("ratingsBySeason = " + ratingsBySeason);

        DoubleSummaryStatistics est = episodeList.stream()
                .filter(e -> e.getRating() > 0.0)
                .collect(Collectors.summarizingDouble(Episode::getRating));
        System.out.println("est = " + est);
        System.out.println("Average: " + est.getAverage());
        System.out.println("Max Rating: " + est.getMax());
        System.out.println("Min Rating: " + est.getMin());
        Stream.iterate(0, n -> n + 1)
                .limit(10)
                .forEach(System.out::println);*/