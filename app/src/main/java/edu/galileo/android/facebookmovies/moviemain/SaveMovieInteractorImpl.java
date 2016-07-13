package edu.galileo.android.facebookmovies.moviemain;

import edu.galileo.android.facebookmovies.entities.Movie;

/**
 * Created by ajv.
 */
public class SaveMovieInteractorImpl implements SaveMovieInteractor {
    MovieMainRepository repository;

    public SaveMovieInteractorImpl(MovieMainRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(Movie movie) {
        repository.saveMovie(movie);
    }
}
