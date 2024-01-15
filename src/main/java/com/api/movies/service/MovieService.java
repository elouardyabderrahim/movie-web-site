package com.api.movies.service;

import com.api.movies.entities.Movie;
import com.api.movies.repository.MovieRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    private final MovieRepository movieRepository;
     MovieService(MovieRepository movieRepository){
         this.movieRepository = movieRepository;
    }

    public List<Movie> allMovies(){
        return movieRepository.findAll();
    }

    public Optional<Movie> MovieById(ObjectId id) {

         return movieRepository.findById(id);
    }

    public Optional<Movie> MovieByImdbId(String imdbId) {

        return movieRepository.findByImdbId(imdbId);
    }
}
