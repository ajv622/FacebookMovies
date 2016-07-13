package edu.galileo.android.facebookmovies.lib;

import android.app.Activity;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestListener;

import edu.galileo.android.facebookmovies.R;

/**
 * Created by ajv.
 */
public class GlideImageLoader implements ImageLoader {
    private RequestManager glideRequestManager;
    private RequestListener onFinishedImageLoadingListener;

    public void setLoaderContext(Activity activity) {
        this.glideRequestManager = Glide.with(activity);
    }

    @Override
    public void load(ImageView imageView, String URL) {
        if (onFinishedImageLoadingListener != null) {
                glideRequestManager
                        .load(URL)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .listener(onFinishedImageLoadingListener)
                        .into(imageView);
        } else {
                glideRequestManager
                        .load(URL)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(imageView);
        }
    }

    @Override
    public void setOnFinishedImageLoadingListener(Object listener) {
        try {
            this.onFinishedImageLoadingListener = (RequestListener) listener;
        } catch (ClassCastException e) {
            Log.e(this.getClass().getName(),e.getMessage());
        }
    }
}
