package com.driver;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MovieRepository {
    HashMap<String, Movie> movieDb = new HashMap<>();
    HashMap<String, Director> directorDb = new HashMap<>();
    HashMap<Director, List<Movie>> movieDirectorDb = new HashMap<>();

    public String addMovieInDb(Movie movie) {
        String name = movie.getName();
        movieDb.put(name, movie);
        return "Successfully added the movie";
    }


    public String addDirectorInDb(Director director) {
        String name = director.getName();
        directorDb.put(name, director);
        return "Successfully added the director";
    }

    public Movie getMovieByNameFromDb(String searchName) {
        return movieDb.getOrDefault(searchName, null);

    }

    public Director getDirectorByNameFromDb(String searchName) {
        return directorDb.getOrDefault(searchName, null);
    }

    public String addMovieDirectorPairFromDb(String movieName, String directorName) {
        Movie movie = getMovieByNameFromDb(movieName);
        if (movieDirectorDb.containsKey(getDirectorByNameFromDb(directorName))) {

            List<Movie> movieList = movieDirectorDb.get(getDirectorByNameFromDb(directorName));
            movieList.add(movie);
            movieDirectorDb.put(getDirectorByNameFromDb(directorName), movieList);
        } else {
            List<Movie> movieList = new ArrayList<>();
            movieList.add(movie);
            movieDirectorDb.put(getDirectorByNameFromDb(directorName), movieList);
        }
        return "Successfully created movie director pair";
    }


    public List<String> getMoviesByDirectorNameFromDb(String directorName) {
        Director director = directorDb.get(directorName);
        List<Movie> movieList = movieDirectorDb.get(director);
        List<String> movieNameList = new ArrayList<>();
        for (Movie movie : movieList) {
            movieNameList.add(movie.getName());
        }
        return movieNameList;
    }

    public List<String> findAllMoviesFromDb() {
        List<String> allMovieList = new ArrayList<>();
        for (Map.Entry<String, Movie> set :
                movieDb.entrySet()) {
            allMovieList.add(set.getKey());
        }
        return allMovieList;
    }

    public String deleteDirectorByNameFromDb(String directorName) {
        Director director = directorDb.get(directorName);
        List<Movie> movieList = movieDirectorDb.get(director);
        for (Movie movie : movieList) {
            movieDb.remove(movie.getName());
        }
        directorDb.remove(directorName);
        movieDirectorDb.remove(director);

        return "Successfully deleted movies related to given director";
    }

    public String deleteAllDirectorsFromDb() {
        List<Director> directors = new ArrayList<>();
        for (Map.Entry<Director, List<Movie>> set :
                movieDirectorDb.entrySet()) {
            List<Movie> movieList = set.getValue();
            for (Movie movie : movieList) {
                movieDb.remove(movie.getName());
            }

            directors.add(set.getKey());
            directorDb.remove(set.getKey().getName());
        }
        for (Director director : directors
        ) {
            movieDirectorDb.remove(director);
        }
        return "Successfully deleted all directors and movies";
    }

    public String getDirectorFromMovieNameFromDb(String movieName) {
        String directorName=null;
        for (Map.Entry<Director, List<Movie>> set :
                movieDirectorDb.entrySet()) {
            List<Movie> movieList = set.getValue();
            for (Movie movie : movieList) {
                if (movie.getName().equals(movieName)) {
                    directorName=set.getKey().getName();
                    break;
                }
            }
        }
        return directorName;
    }
}
