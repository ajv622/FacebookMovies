package edu.galileo.android.facebookmovies.moviemain.ui;

import edu.galileo.android.facebookmovies.entities.Movie;

/**
 * Created by ajv.
 */
public interface MovieMainView {
    void showProgress();
    void hideProgress();
    void showUIElements();
    void hideUIElements();
    void saveAnimation();
    void dismissAnimation();

    void onMovieSaved();
    void setMovie(Movie movie);
    void onGetMovieError(String error);
}
