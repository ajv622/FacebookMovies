package edu.galileo.android.facebookmovies.movielist;

import edu.galileo.android.facebookmovies.entities.Movie;

/**
 * Created by ajv.
 */
public interface MovieListRepository {
    void getSavedMovies();
    void updateMovie(Movie movie);
    void removeMovie(Movie movie);
}
