package example.nano.pop_movie_stage2.utilities;

import example.nano.pop_movie_stage2.BuildConfig;

/**
 * Created by ehab- on 4/16/2017.
 */

public class Urls {
    public static final String SORT_BY_POPULAR ="http://api.themoviedb.org/3/movie/popular?api_key="+ BuildConfig.tmdb_api_key;
    public static final String SORT_BY_TOP_RATED ="http://api.themoviedb.org/3/movie/top_rated?api_key="+ BuildConfig.tmdb_api_key;
    public static final String POSTER="http://image.tmdb.org/t/p/w";
}
