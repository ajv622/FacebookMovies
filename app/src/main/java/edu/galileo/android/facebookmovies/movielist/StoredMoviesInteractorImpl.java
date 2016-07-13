package edu.galileo.android.facebookmovies.movielist;

import edu.galileo.android.facebookmovies.entities.Movie;

/**
 * Created by ajv.
 */
public class StoredMoviesInteractorImpl implements StoredMoviesInteractor {
    private MovieListRepository repository;

    public StoredMoviesInteractorImpl(MovieListRepository repository) {
        this.repository = repository;
    }

    @Override
    public void executeUpdate(Movie movie) {
        repository.updateMovie(movie);
    }

    @Override
    public void executeDelete(Movie movie) {
        repository.removeMovie(movie);
    }
}
