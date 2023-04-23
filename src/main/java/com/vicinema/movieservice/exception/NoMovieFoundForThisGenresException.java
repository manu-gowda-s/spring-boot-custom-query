package com.vicinema.movieservice.exception;

public class NoMovieFoundForThisGenresException extends Throwable {
    public NoMovieFoundForThisGenresException(String message)
    {
        super(message);
    }
}
