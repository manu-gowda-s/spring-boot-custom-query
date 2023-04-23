package com.vicinema.movieservice.repo;

import com.vicinema.movieservice.model.Genres;
import com.vicinema.movieservice.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Integer>
{

    //to search movie by name
    //by using JPQL
    @Query("SELECT m FROM Movie m WHERE m.movieName=?1")
    Movie getMovieByMovieName(String movieName);

    //query to list of movies by genres JPQL
    @Query("SELECT m FROM Movie m WHERE m.genres= :genres")
    List<Movie> getMoviesByGenres(@Param("genres")Genres genres);

    // filter movie based on ratting using (Native SQL query)
    @Query(value = "SELECT * FROM Movie ORDER BY ratting DESC", nativeQuery = true)
    List<Movie> findMovieByRatting();

    // Get movies By Language and by top ratings By SQL Query
    @Query(value = "SELECT * FROM Movie WHERE language= :language ORDER BY ratting DESC", nativeQuery = true)
    List<Movie> getMovieByLanguage(@Param("language") String language);

    @Query(value = "SELECT * FROM Movie WHERE language= :language AND availability = true", nativeQuery = true)
    List<Movie> getAvailbleMovies(@Param("language") String language);


}
