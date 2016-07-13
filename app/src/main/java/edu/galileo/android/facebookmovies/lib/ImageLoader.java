package edu.galileo.android.facebookmovies.lib;

import android.widget.ImageView;

import edu.galileo.android.facebookmovies.entities.Movie;

/**
 * Created by ajv.
 */
public interface ImageLoader {
    void load(ImageView imageView, String URL);
    void setOnFinishedImageLoadingListener(Object object);
}
