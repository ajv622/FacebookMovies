package edu.galileo.android.facebookmovies;

import android.app.Application;

import com.facebook.FacebookSdk;
import com.raizlabs.android.dbflow.config.FlowManager;

import edu.galileo.android.facebookmovies.lib.di.LibsModule;
import edu.galileo.android.facebookmovies.movielist.di.DaggerMovieListComponent;
import edu.galileo.android.facebookmovies.movielist.di.MovieListComponent;
import edu.galileo.android.facebookmovies.movielist.di.MovieListModule;
import edu.galileo.android.facebookmovies.movielist.ui.MovieListActivity;
import edu.galileo.android.facebookmovies.movielist.ui.MovieListView;
import edu.galileo.android.facebookmovies.movielist.ui.adapters.OnItemClickListener;
import edu.galileo.android.facebookmovies.moviemain.di.DaggerMovieMainComponent;
import edu.galileo.android.facebookmovies.moviemain.di.MovieMainComponent;
import edu.galileo.android.facebookmovies.moviemain.di.MovieMainModule;
import edu.galileo.android.facebookmovies.moviemain.ui.MovieMainActivity;
import edu.galileo.android.facebookmovies.moviemain.ui.MovieMainView;

/**
 * Created by ajv.
 */
public class FacebookMoviesApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initDB();
        initFacebook();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        DBTearDown();
    }

    private void initDB() {
        FlowManager.init(this);
    }

    private void DBTearDown() {
        FlowManager.destroy();
    }

    private void initFacebook() {
        FacebookSdk.sdkInitialize(getApplicationContext());
    }

    public MovieMainComponent getMovieMainComponent(MovieMainActivity activity, MovieMainView view) {
        return DaggerMovieMainComponent
                .builder()
                .libsModule(new LibsModule(activity))
                .movieMainModule(new MovieMainModule(view))
                .build();
    }

    public MovieListComponent getMovieListComponent(MovieListActivity activity, MovieListView view, OnItemClickListener onItemClickListener) {
        return DaggerMovieListComponent
                .builder()
                .libsModule(new LibsModule(activity))
                .movieListModule(new MovieListModule(view, onItemClickListener))
                .build();
    }
}
