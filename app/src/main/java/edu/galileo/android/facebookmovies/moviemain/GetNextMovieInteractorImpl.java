package edu.galileo.android.facebookmovies.moviemain;

import java.util.Random;

/**
 * Created by ajv.
 */
public class GetNextMovieInteractorImpl implements GetNextMovieInteractor {
    MovieMainRepository repository;

    public GetNextMovieInteractorImpl(MovieMainRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute() {
        repository.getNextMovie();
    }

}
