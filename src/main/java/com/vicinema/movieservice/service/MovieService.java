package com.vicinema.movieservice.service;

import com.vicinema.movieservice.exception.MovieNameIncorrectException;
import com.vicinema.movieservice.exception.MovieNotFoundException;
import com.vicinema.movieservice.exception.NoMovieFoundForThisGenresException;
import com.vicinema.movieservice.exception.UpdateFailedException;
import com.vicinema.movieservice.model.Genres;
import com.vicinema.movieservice.model.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieService
{

    Movie addMovie(Movie movie);

    Optional<Movie> getMovieByMovieId(Integer movieId) throws MovieNotFoundException;

    List<Movie> getAllMovies();

    Movie updateMovie(Movie movie, Integer movieId) throws UpdateFailedException;

    Movie findMovieByName(String movieName) throws MovieNameIncorrectException;

    List<Movie> findAllMovieListsByGenres(Genres genres) throws NoMovieFoundForThisGenresException;

    List<Movie> getMoviesByRattingDesc();

    List<Movie> getTopMoviesByLanguages(String language);

    List<Movie> getAvailableMoviesList(String language);

}
