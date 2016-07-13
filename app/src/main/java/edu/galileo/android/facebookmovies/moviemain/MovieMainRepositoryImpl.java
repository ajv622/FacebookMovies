package edu.galileo.android.facebookmovies.moviemain;

import android.os.AsyncTask;
import android.util.Log;

import java.util.List;
import java.util.Random;

import edu.galileo.android.facebookmovies.BuildConfig;
import edu.galileo.android.facebookmovies.entities.Movie;
import edu.galileo.android.facebookmovies.lib.EventBus;
import edu.galileo.android.facebookmovies.moviemain.events.MovieMainEvent;
import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.TmdbMovies;
import info.movito.themoviedbapi.model.Artwork;
import info.movito.themoviedbapi.model.ArtworkType;
import info.movito.themoviedbapi.model.Discover;
import info.movito.themoviedbapi.model.MovieDb;

/**
 * Created by ajv.
 */
public class MovieMainRepositoryImpl implements MovieMainRepository {

    private static final String TAG = "MovieMainRepositoryImpl";
    private EventBus eventBus;

    public MovieMainRepositoryImpl(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public void getNextMovie() {
        GetMovieAsyncTask task = new GetMovieAsyncTask();
        task.execute();
    }


    @Override
    public void saveMovie(Movie movie) {
        Log.d(TAG, "Guardando la película: " + movie);
        movie.save();
        post();
    }

    private void post() {
        MovieMainEvent event = new MovieMainEvent();
        event.setType(MovieMainEvent.SAVE_EVENT);
        eventBus.post(event);
    }

    private void post(String error, int type) {
        MovieMainEvent event = new MovieMainEvent();
        event.setType(type);
        event.setError(error);
        eventBus.post(event);
    }

    private void post(Movie movie) {
        MovieMainEvent event = new MovieMainEvent();
        event.setType(MovieMainEvent.NEXT_EVENT);
        event.setMovie(movie);
        eventBus.post(event);
    }


    private class GetMovieAsyncTask extends AsyncTask<Void, Integer, Movie> {

        @Override
        protected Movie doInBackground(Void... params) {
            Log.d(TAG, "Obteniendo película...");

            TmdbApi tmdb = new TmdbApi(BuildConfig.MOVIES_API_KEY);

            MovieDb movieDb = null;
            int id = -1;
            do {
                try {
                    Random r = new Random();
                    id = r.nextInt(10191) + 1;

                    movieDb = tmdb.getMovies().getMovie(id, "en", TmdbMovies.MovieMethod.images);

                    List<Artwork> images = movieDb.getImages(ArtworkType.POSTER);
                    if (images == null || images.isEmpty() || movieDb.getImdbID() == null) {
                        movieDb = null;
                    }
                } catch (RuntimeException e) {
                    Log.e(TAG, "Problem reading movie with id = " + id);
                }
            } while (movieDb == null);

            Movie movie;

            try {
                Log.d(TAG, "Película con id: " + movieDb.getImdbID());
                movie = new Movie();
                movie.setMovieId(movieDb.getImdbID());

                Log.d(TAG, "Titulo: " + movieDb.getTitle());
                movie.setTitle(movieDb.getTitle());

                List<Artwork> images = movieDb.getImages(ArtworkType.POSTER);
                String imageFile;
                if (images != null && !images.isEmpty()) {
                    imageFile = "http://image.tmdb.org/t/p/w500" + images.get(0).getFilePath();
                    Log.d(TAG, "IMAGEN: " + imageFile);
                } else {
                    Log.d(TAG, "SIN IMAGEN!");
                    imageFile = null;
                }
                movie.setSourceURL("http://www.imdb.com/title/" + movieDb.getImdbID() + "/");
                Log.d(TAG, "Homepage = " + movie.getSourceURL());
                movie.setImageURL(imageFile);
                movie.setTitle(movieDb.getTitle());
            } catch (RuntimeException e) {
                Log.e(TAG, "Problema construyendo la película", e);
                movie = null;
            }

            return movie;
        }

        @Override
        protected void onPostExecute(Movie movie) {
            if (movie != null) {
                Log.d(TAG, "Nueva peli: " + movie);
                post(movie);
            } else {
                post("No hay película", MovieMainEvent.NEXT_EVENT);
            }
        }

        @Override
        protected void onCancelled() {
            post("Tarea cancelada", MovieMainEvent.NEXT_EVENT);
        }
    }
}
