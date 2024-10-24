package com.oracleone.screenmatch.main;

import com.oracleone.screenmatch.model.*;
import com.oracleone.screenmatch.repository.ISeriesRepository;
import com.oracleone.screenmatch.service.ConvertData;
import com.oracleone.screenmatch.service.FetchApi;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private final String API_KEY_OMDB = "&apikey=6ee384bf";
    private final String URL = "https://www.omdbapi.com/?t=";
    private final Scanner sc = new Scanner(System.in);
    private final FetchApi fetchApi = new FetchApi();
    private final ConvertData convertData = new ConvertData();
    List<Series> series;
    private ISeriesRepository repository;
    private List<SeriesData> seriesDataList = new ArrayList<>();
    private Optional<Series> seriesFound;

    public Main(ISeriesRepository repository) {
        this.repository = repository;
    }

    public void start() {
        var opt = -1;
        while (opt != 0) {
            var menu = """
                    \n>===Select an option===<
                    
                    1 - Search series
                    2 - Search episodes
                    3 - Show searched series
                    4 - Search series by title
                    5 - Top 5 best series
                    6 - Search series by category
                    7 - Filter series
                    8 - Search episodes by title
                    9 - Search top 5 episodes
                    0 - Exit
                    
                    >===                  ===<
                    """;


            System.out.println(menu);
            opt = sc.nextInt();
            sc.nextLine();

            switch (opt) {
                case 1:
                    searchSeries();
                    break;
                case 2:
                    searchEpisodesBySeries();
                    break;
                case 3:
                    showSearchedSeries();
                    break;
                case 4:
                    searchSeriesByTitle();
                    break;
                case 5:
                    searchTop5Series();
                    break;
                case 6:
                    searchByCategory();
                    break;
                case 7:
                    filterSeries();
                    break;
                case 8:
                    searchEpisodesByTitle();
                    break;
                case 9:
                    searchTop5Episodes();
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
        System.out.println();

        var json = fetchApi.getData(
                URL +
                        seriesName +
                        API_KEY_OMDB);

        var data = convertData.getData(json, SeriesData.class);

        if (data.title() == null) {
            throw new NullPointerException();
        }
        return data;
    }

    private void searchEpisodesBySeries() {
        //SeriesData seriesData = getSeriesData();
        showSearchedSeries();
        System.out.print("Enter the name of the series you want to watch the episodes of: ");
        var seriesName = sc.nextLine().toLowerCase();

        Optional<Series> aux = series.stream()
                .filter(s -> s.getTitle().toLowerCase().contains(seriesName))
                .findFirst();

        if (aux.isPresent()) {
            var seriesFound = aux.get();
            List<SeasonData> seasons = new ArrayList<>();

            for (int i = 1; i <= seriesFound.getSeasons(); i++) {
                var json = fetchApi.getData(
                        URL +
                                seriesFound.getTitle().replace(" ", "+") +
                                "&season=" +
                                i +
                                API_KEY_OMDB);

                var seasonData = convertData.getData(json, SeasonData.class);
                seasons.add(seasonData);
            }

            seasons.forEach(System.out::println);

            List<Episode> episodes = seasons.stream()
                    .flatMap(d -> d.episodes().stream()
                            .map(e -> new Episode(d.seasonNumber(), e)))
                    .collect(Collectors.toList());

            seriesFound.setEpisodes(episodes);
            repository.save(seriesFound);
        } else {
            System.out.println("Series not found");
        }


    }

    private void searchSeries() {
        try {
            SeriesData seriesData = getSeriesData();
            Series series = new Series(seriesData);
            repository.save(series);
            //seriesDataList.add(seriesData);
            System.out.println("Found: " + seriesData);
        } catch (NullPointerException e) {
            System.out.println("Series not found");
        }
    }

    private void showSearchedSeries() {
        series = repository.findAll();

        series.stream()
                .sorted(Comparator.comparing(Series::getGenre))
                .forEach(System.out::println);

    }

    private void searchSeriesByTitle() {
        System.out.print("Enter the name of the series you want to search: ");
        var seriesName = sc.nextLine().toLowerCase();

        seriesFound = repository.findByTitleContainsIgnoreCase(seriesName);

        if (seriesFound.isPresent()) {
            System.out.println("Series found = " + seriesFound.get());
        } else {
            System.out.println("Series not found");
        }
    }

    private void searchTop5Series() {
        List<Series> topSeries = repository.findTop5ByOrderByRatingDesc();
        topSeries.forEach(s -> System.out.println("Series: " + s.getTitle() +
                " - Rating: " + s.getRating()));
    }

    private void searchByCategory() {
        System.out.print("Enter the category of the series you want to search: ");

        try {
            var genre = Category.fromString(sc.nextLine());

            List<Series> seriesFound = repository.findByGenre(genre);

            seriesFound.forEach(s -> System.out.println("Series: " + s.getTitle() +
                    " - Category: " + s.getGenre()));

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void filterSeries() {
        System.out.print("Filter series by number of seasons: ");
        var seasons = sc.nextInt();
        sc.nextLine();

        System.out.print("What rating: ");
        var rating = sc.nextDouble();
        sc.nextLine();

        /*List<Series> filteredSeries = repository.findBySeasonsLessThanEqualAndRatingGreaterThanEqual
                (seasons, rating);*/
        List<Series> filteredSeries = repository.seriesBySeasonAndRating(seasons, rating);

        System.out.println("**Filtered Series**");
        filteredSeries.forEach(s -> System.out.println("Series: " + s.getTitle() +
                " - Rating: " + s.getRating()));
    }

    private void searchEpisodesByTitle() {
        System.out.print("Enter the name of the episode you want to search: ");
        var episodeTitle = sc.nextLine();

        List<Episode> foundEpisodes = repository.episodesByName(episodeTitle);
        foundEpisodes.forEach(e -> System.out.printf("Series: %s - Season %d - Episode: %s - Rating: %.2f\n",
                e.getSeries().getTitle(), e.getSeasonNumber(), e.getTitle(), e.getRating()));
    }

    private void searchTop5Episodes() {
        searchSeriesByTitle();
        if (seriesFound.isPresent()) {
            Series series = seriesFound.get();
            List<Episode> top5Episodes = repository.top5Episodes(series);
            top5Episodes.forEach(e -> System.out.printf("Series: %s - Season %d - Episode: %s - Rating: %.2f\n",
                    e.getSeries().getTitle(), e.getSeasonNumber(), e.getTitle(), e.getRating()));
        }
    }

}