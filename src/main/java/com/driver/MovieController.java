package com.driver;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController

public class MovieController {
    @Autowired
    MovieService movieService;

    @PostMapping("/add-movie")
    public ResponseEntity<String> addMovie(@RequestBody() Movie movie) {
        String response = movieService.addMovie(movie);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/add-director")
    public ResponseEntity<String> addDirector(@RequestBody() Director director) {
        String response = movieService.addDirector(director);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/add-movie-director-pair")
    public ResponseEntity<String> addMovieDirectorPair(@RequestParam("movieName") String movieName, @RequestParam("directorName") String directorName) {
        String response = movieService.addMovieDirectorPair(movieName, directorName);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/get-movie-by-name")
    public ResponseEntity<Movie> getMovieByName(@RequestParam("name") String searchName) {
        Movie movie = movieService.getMovieByName(searchName);
        if (movie == null)
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(movie, HttpStatus.OK);

    }

    @GetMapping("/get-director-by-name")
    public ResponseEntity<Director> getDirectorByName(@RequestParam("name") String searchName) {
        Director director = movieService.getDirectorByName(searchName);
        if (director == null)
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(director, HttpStatus.OK);

    }

    @GetMapping("/get-movies-by-director-name/{directorName}")
    public ResponseEntity<List<String>> getMoviesByDirectorName(@PathVariable("directorName") String directorName) {
        List<String> movieList = new ArrayList<>();
        movieList = movieService.getMoviesByDirectorName(directorName);
        if (movieList == null)
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(movieList, HttpStatus.OK);

    }

    @GetMapping("/get-all-movies")
    public ResponseEntity<List<String>> findAllMovies() {
        List<String> allMovieList = new ArrayList<>();
        allMovieList = movieService.findAllMovies();
        if (allMovieList == null)
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(allMovieList, HttpStatus.OK);
    }

    @DeleteMapping("/delete-director-by-name")
    public ResponseEntity<String> deleteDirectorByName(@RequestParam("directorName") String directorName) {
        String response = movieService.deleteDirectorByName(directorName);
        if (response == null)
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
 @DeleteMapping("/delete-all-directors")
    public ResponseEntity<String> deleteAllDirectors(){
        String response= movieService.deleteAllDirectors();
     if (response == null)
         return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
     return new ResponseEntity<>(response, HttpStatus.OK);
 }

 @GetMapping("/get-director-from-movieName")
    public ResponseEntity<String> getDirectorFromMovieName(@RequestParam("movieName") String movieName){
        String response = movieService.getDirectorFromMovieName(movieName);
     if (response == null)
         return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
     return new ResponseEntity<>(response, HttpStatus.OK);

 }

}
