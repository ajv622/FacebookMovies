package edu.galileo.android.facebookmovies.db;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by ajv
 */
@Database(name = MoviesDatabase.NAME, version = MoviesDatabase.VERSION)
public class MoviesDatabase {
    public static final int VERSION = 1;
    public static final String NAME = "Movies";
}
