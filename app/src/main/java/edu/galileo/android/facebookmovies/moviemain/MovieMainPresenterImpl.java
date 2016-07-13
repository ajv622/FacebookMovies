package edu.galileo.android.facebookmovies.moviemain;

import org.greenrobot.eventbus.Subscribe;

import edu.galileo.android.facebookmovies.entities.Movie;
import edu.galileo.android.facebookmovies.lib.EventBus;
import edu.galileo.android.facebookmovies.moviemain.events.MovieMainEvent;
import edu.galileo.android.facebookmovies.moviemain.ui.MovieMainView;

/**
 * Created by ajv.
 */
public class MovieMainPresenterImpl implements MovieMainPresenter {
    EventBus eventBus;
    MovieMainView view;
    SaveMovieInteractor saveMovie;
    GetNextMovieInteractor getNextRecipe;

    public MovieMainPresenterImpl(EventBus eventBus, MovieMainView view, SaveMovieInteractor saveMovie, GetNextMovieInteractor getNextRecipe) {
        this.view = view;
        this.eventBus = eventBus;
        this.saveMovie = saveMovie;
        this.getNextRecipe = getNextRecipe;
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        eventBus.unregister(this);
        view = null;
    }

    @Override
    public MovieMainView getView() {
        return this.view;
    }

    @Override
    public void saveMovie(Movie movie) {
        if (this.view != null){
            view.saveAnimation();
            view.hideUIElements();
            view.showProgress();
        }
        saveMovie.execute(movie);
    }

    @Override
    public void dismissMovie() {
        if (this.view != null) {
            view.dismissAnimation();
        }
        getNextMovie();
    }

    @Override
    public void getNextMovie(){
        if (this.view != null) {
            view.showProgress();
            view.hideUIElements();
        }
        getNextRecipe.execute();

    }

    @Override
    @Subscribe
    public void onEventMainThread(MovieMainEvent event) {
        if (this.view != null){
            String error = event.getError();
            if (error == null) {
                if (event.getType() == MovieMainEvent.NEXT_EVENT) {
                    view.setMovie(event.getMovie());
                } else if (event.getType() == MovieMainEvent.SAVE_EVENT) {
                    view.onMovieSaved();
                    getNextRecipe.execute();
                }

            } else {
                view.hideProgress();
                view.onGetMovieError(error);
            }
        }
    }
}
