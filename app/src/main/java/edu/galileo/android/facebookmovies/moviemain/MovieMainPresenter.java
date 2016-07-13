package edu.galileo.android.facebookmovies.moviemain;

import edu.galileo.android.facebookmovies.entities.Movie;
import edu.galileo.android.facebookmovies.moviemain.events.MovieMainEvent;
import edu.galileo.android.facebookmovies.moviemain.ui.MovieMainView;

/**
 * Created by ajv.
 */
public interface MovieMainPresenter {
    void onCreate();
    void onDestroy();

    void dismissMovie();
    void getNextMovie();
    void saveMovie(Movie movie);
    void onEventMainThread(MovieMainEvent event);

    MovieMainView getView();
}
