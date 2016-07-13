package edu.galileo.android.facebookmovies.moviemain;

import edu.galileo.android.facebookmovies.entities.Movie;

/**
 * Created by ajv.
 */
public interface MovieMainRepository {
    void getNextMovie();
    void saveMovie(Movie movie);
}
