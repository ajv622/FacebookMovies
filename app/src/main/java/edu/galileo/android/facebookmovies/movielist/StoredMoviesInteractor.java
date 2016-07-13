package edu.galileo.android.facebookmovies.movielist;

import edu.galileo.android.facebookmovies.entities.Movie;

/**
 * Created by ajv.
 */
public interface StoredMoviesInteractor {
    void executeUpdate(Movie movie);
    void executeDelete(Movie movie);
}
