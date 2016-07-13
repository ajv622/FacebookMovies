package edu.galileo.android.facebookmovies.movielist.ui.adapters;

import edu.galileo.android.facebookmovies.entities.Movie;

/**
 * Created by ajv.
 */
public interface OnItemClickListener {
    void onFavClick(Movie movie);
    void onItemClick(Movie movie);
    void onDeleteClick(Movie movie);
}
