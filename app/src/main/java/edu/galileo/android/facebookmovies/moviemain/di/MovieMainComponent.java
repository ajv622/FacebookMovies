package edu.galileo.android.facebookmovies.moviemain.di;

import javax.inject.Singleton;

import dagger.Component;
import edu.galileo.android.facebookmovies.lib.ImageLoader;
import edu.galileo.android.facebookmovies.lib.di.LibsModule;
import edu.galileo.android.facebookmovies.moviemain.MovieMainPresenter;

/**
 * Created by ajv.
 */
@Singleton
@Component(modules = {MovieMainModule.class, LibsModule.class})
public interface MovieMainComponent {
    ImageLoader getImageLoader();
    MovieMainPresenter getPresenter();
}
