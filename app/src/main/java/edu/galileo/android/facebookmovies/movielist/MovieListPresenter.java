package edu.galileo.android.facebookmovies.movielist;

import edu.galileo.android.facebookmovies.entities.Movie;
import edu.galileo.android.facebookmovies.movielist.events.MovieListEvent;
import edu.galileo.android.facebookmovies.movielist.ui.MovieListView;

/**
 * Created by ajv.
 */
public interface MovieListPresenter {
    void onCreate();
    void onDestroy();

    void getMovies();
    void removeMovie(Movie movie);
    void toggleFavorite(Movie movie);
    void onEventMainThread(MovieListEvent event);

    MovieListView getView();
}
