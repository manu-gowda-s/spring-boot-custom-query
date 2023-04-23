package com.vicinema.movieservice.controller;

import com.vicinema.movieservice.exception.MovieNameIncorrectException;
import com.vicinema.movieservice.exception.MovieNotFoundException;
import com.vicinema.movieservice.exception.NoMovieFoundForThisGenresException;
import com.vicinema.movieservice.exception.UpdateFailedException;
import com.vicinema.movieservice.model.Genres;
import com.vicinema.movieservice.model.Movie;
import com.vicinema.movieservice.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(value = "/api/v1")
public class MovieController
{

    @Autowired
    private MovieService movieService;

    //adding new movie
    // ADDING THE LOCATION HEADER
    @PostMapping("/movie")
    public ResponseEntity<Movie> addNewMovie(@RequestBody Movie movie)
    {
        Movie movie1 = movieService.addMovie(movie);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/id/{movieId}")
                .buildAndExpand(movie1.getMovieId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    //get movie by
    @GetMapping("/movie/id/{movieId}")
    public EntityModel<Movie> getMovieById(@PathVariable Integer movieId) throws MovieNotFoundException
    {
        Optional<Movie> optionalMovie = movieService.getMovieByMovieId(movieId);
        Movie movie = optionalMovie.get();
        //Hateoas
        EntityModel<Movie> entityModel = EntityModel.of(movie);
        WebMvcLinkBuilder linkBuilder = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass())
                .getAllMoviesList());
        entityModel.add(linkBuilder.withRel("All_Movies_list_here"));
        return entityModel;
    }

    //Get all the Movies
    @GetMapping("/movie/all")
    public ResponseEntity<List<Movie>> getAllMoviesList()
    {
        List<Movie> movieLists = movieService.getAllMovies();
        return new ResponseEntity<>(movieLists, HttpStatus.OK);
    }

    //Update Movie Details
    @PutMapping("/movie/update/{movieId}")
    public ResponseEntity<String> updateMovieDetails(@RequestBody Movie movie, @PathVariable Integer movieId)
            throws UpdateFailedException
    {
        Movie updatedMovie = movieService.updateMovie(movie, movieId);
        return new ResponseEntity<>("Movie Updated Successfully!!", HttpStatus.CREATED);
    }

    //Get movie details by name
    @GetMapping("/movie/name/{movieName}")
    public ResponseEntity<Movie> getMovieByMovieName(@PathVariable String movieName)
            throws MovieNameIncorrectException
    {
        Movie movie = movieService.findMovieByName(movieName);
        return new ResponseEntity<>(movie,HttpStatus.OK);

    }

    //Get movie details by name BY GENRES
    @GetMapping("/movie/all/genres/{genres}")
    public ResponseEntity<List<Movie>> getMovieListsByGenres(@PathVariable Genres genres)
            throws NoMovieFoundForThisGenresException
    {
       List<Movie> movieList = movieService.findAllMovieListsByGenres(genres);
       return new ResponseEntity<>(movieList, HttpStatus.OK);
    }

    //movies top by ratting
    @GetMapping("/movie/ratting/high")
    public ResponseEntity<List<Movie>> movieByRatting()
    {
        List<Movie>  movieList = movieService.getMoviesByRattingDesc();
        return new ResponseEntity<>(movieList, HttpStatus.OK);
    }

    @GetMapping("/movie/top/language/{language}")
    public ResponseEntity<List<Movie>> getMoviesByLanguageAndTheirRatings(@PathVariable String language)
    {
        List<Movie> movieList = movieService.getTopMoviesByLanguages(language);
        return new ResponseEntity<>(movieList, HttpStatus.OK);
    }

    //gET ALL availble Movies
    @GetMapping("/movie/availble/language/{language}")
    public ResponseEntity<List<Movie>> getAllAvailableMovies(@PathVariable String language)
    {
        List<Movie> movieList = movieService.getAvailableMoviesList(language);
        return new ResponseEntity<>(movieList, HttpStatus.OK);
    }




}
