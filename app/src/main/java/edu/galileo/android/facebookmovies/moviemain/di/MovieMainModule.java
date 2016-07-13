package edu.galileo.android.facebookmovies.moviemain.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import edu.galileo.android.facebookmovies.lib.EventBus;
import edu.galileo.android.facebookmovies.moviemain.GetNextMovieInteractor;
import edu.galileo.android.facebookmovies.moviemain.GetNextMovieInteractorImpl;
import edu.galileo.android.facebookmovies.moviemain.MovieMainPresenter;
import edu.galileo.android.facebookmovies.moviemain.MovieMainPresenterImpl;
import edu.galileo.android.facebookmovies.moviemain.MovieMainRepository;
import edu.galileo.android.facebookmovies.moviemain.MovieMainRepositoryImpl;
import edu.galileo.android.facebookmovies.moviemain.SaveMovieInteractor;
import edu.galileo.android.facebookmovies.moviemain.SaveMovieInteractorImpl;
import edu.galileo.android.facebookmovies.moviemain.ui.MovieMainView;

/**
 * Created by ajv.
 */
@Module
public class MovieMainModule {
    MovieMainView view;

    public MovieMainModule(MovieMainView view) {
        this.view = view;
    }

    @Provides @Singleton
    MovieMainView provideRecipeMainView() {
        return this.view;
    }

    @Provides @Singleton
    MovieMainPresenter provideRecipeMainPresenter(EventBus eventBus, MovieMainView view, SaveMovieInteractor save, GetNextMovieInteractor getNext) {
        return new MovieMainPresenterImpl(eventBus, view, save, getNext);
    }

    @Provides @Singleton
    SaveMovieInteractor provideSaveRecipeInteractor(MovieMainRepository repository) {
        return new SaveMovieInteractorImpl(repository);
    }

    @Provides @Singleton
    GetNextMovieInteractor provideGetNextRecipeInteractor(MovieMainRepository repository) {
        return new GetNextMovieInteractorImpl(repository);
    }

    @Provides @Singleton
    MovieMainRepository provideRecipeMainRepository(EventBus eventBus) {
        return new MovieMainRepositoryImpl(eventBus);
    }
}
