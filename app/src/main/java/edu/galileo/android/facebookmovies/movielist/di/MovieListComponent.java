package edu.galileo.android.facebookmovies.movielist.di;

import javax.inject.Singleton;

import dagger.Component;
import edu.galileo.android.facebookmovies.lib.di.LibsModule;
import edu.galileo.android.facebookmovies.movielist.MovieListPresenter;
import edu.galileo.android.facebookmovies.movielist.ui.adapters.MoviesAdapter;

/**
 * Created by ajv.
 */
@Singleton
@Component(modules = {MovieListModule.class, LibsModule.class})
public interface MovieListComponent {
    MovieListPresenter getPresenter();
    MoviesAdapter getAdapter();
}
