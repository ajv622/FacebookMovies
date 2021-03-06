package edu.galileo.android.facebookmovies.movielist.events;

import java.util.List;

import edu.galileo.android.facebookmovies.entities.Movie;

/**
 * Created by ajv.
 */
public class MovieListEvent {
    private int type;
    private List<Movie> movies;
    public final static int READ_EVENT = 0;
    public final static int UPDATE_EVENT = 1;
    public final static int DELETE_EVENT = 2;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}
