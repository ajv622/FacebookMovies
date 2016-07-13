package edu.galileo.android.facebookmovies.movielist;

/**
 * Created by ajv.
 */
public class MovieListInteractorImpl implements MovieListInteractor {
    private MovieListRepository repository;

    public MovieListInteractorImpl(MovieListRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute() {
        repository.getSavedMovies();
    }
}
