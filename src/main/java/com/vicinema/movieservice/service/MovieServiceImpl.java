package com.vicinema.movieservice.service;

import com.vicinema.movieservice.exception.MovieNameIncorrectException;
import com.vicinema.movieservice.exception.MovieNotFoundException;
import com.vicinema.movieservice.exception.NoMovieFoundForThisGenresException;
import com.vicinema.movieservice.exception.UpdateFailedException;
import com.vicinema.movieservice.model.Genres;
import com.vicinema.movieservice.model.Movie;
import com.vicinema.movieservice.repo.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService
{
    @Autowired
    private MovieRepository movieRepository;

    @Override
    public Movie addMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public Optional<Movie> getMovieByMovieId(Integer movieId) throws MovieNotFoundException {
        Optional<Movie> movie = movieRepository.findById(movieId);
        if(movie.isEmpty()){
            throw new MovieNotFoundException("Movie Not Found! Check the Movie ID again!!");
        }else {
            return movie;
        }
    }

    @Override
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public Movie updateMovie(Movie movie, Integer movieId) throws UpdateFailedException {
        Optional<Movie> movieOptional = movieRepository.findById(movieId);
        if(movieOptional.isEmpty()){
            throw new UpdateFailedException("Unable to Update !! please check the Movie ID !!");
        }else{
            return movieRepository.save(movie);
        }
    }

    @Override
    public Movie findMovieByName(String movieName) throws MovieNameIncorrectException
    {
        Movie movie = movieRepository.getMovieByMovieName(movieName);
        if(movie == null){
            throw new MovieNameIncorrectException("No Movies found !!");
        }else {
            return movie;
        }
    }

    @Override
    public List<Movie> findAllMovieListsByGenres(Genres genres) throws NoMovieFoundForThisGenresException
    {
        List<Movie> movieList = movieRepository.getMoviesByGenres(genres);
        if(movieList.isEmpty()){
            throw new NoMovieFoundForThisGenresException("No Movies found for this Genres!!");
        }else {
            return movieList;
        }
    }

    @Override
    public List<Movie> getMoviesByRattingDesc() {
        return movieRepository.findMovieByRatting();
    }

    @Override
    public List<Movie> getTopMoviesByLanguages(String language) {
        return movieRepository.getMovieByLanguage(language);
    }

    @Override
    public List<Movie> getAvailableMoviesList(String language) {
        return movieRepository.getAvailbleMovies(language);
    }


}
