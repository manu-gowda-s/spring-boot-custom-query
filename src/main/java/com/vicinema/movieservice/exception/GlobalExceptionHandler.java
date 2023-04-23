package com.vicinema.movieservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler
{
    @ExceptionHandler(MovieNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> movieIdNotFoundException(MovieNotFoundException movieNotFoundException)
    {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("message",movieNotFoundException.getMessage());
        return errorMap;
    }

    @ExceptionHandler(UpdateFailedException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> updateFailedException(UpdateFailedException updateFailedException)
    {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("message",updateFailedException.getMessage());
        return errorMap;
    }

    @ExceptionHandler(MovieNameIncorrectException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> nameIncorrectException(MovieNameIncorrectException nameIncorrectException)
    {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("message",nameIncorrectException.getMessage());
        return errorMap;
    }


    @ExceptionHandler(NoMovieFoundForThisGenresException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> genresException(NoMovieFoundForThisGenresException genresException)
    {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("message",genresException.getMessage());
        return errorMap;
    }
}
