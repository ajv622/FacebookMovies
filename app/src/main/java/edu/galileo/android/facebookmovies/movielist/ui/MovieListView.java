package edu.galileo.android.facebookmovies.movielist.ui;

import java.util.List;

import edu.galileo.android.facebookmovies.entities.Movie;

/**
 * Created by ajv.
 */
public interface MovieListView {
    void setMovies(List<Movie> data);
    void movieUpdated();
    void movieDeleted(Movie movie);
}
