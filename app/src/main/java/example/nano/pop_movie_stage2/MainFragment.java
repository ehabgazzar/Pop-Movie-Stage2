package example.nano.pop_movie_stage2;

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import example.nano.pop_movie_stage2.Data.MovieContract;
import example.nano.pop_movie_stage2.models.MovieItem;
import example.nano.pop_movie_stage2.utilities.NetHelper;
import example.nano.pop_movie_stage2.utilities.Urls;
import example.nano.pop_movie_stage2.utilities.Utility;


public class MainFragment extends Fragment{


    private NetHelper mNetHelper;
    Context mContext;
    ArrayList<MovieItem> allItems = null;
    MovieAdapter movieAdapter;
    private RecyclerView recyclerView;
    Gson gson;
    private static final String POPULARITY_DESC = Urls.SORT_BY_POPULAR;
    private static final String RATING_DESC = Urls.SORT_BY_TOP_RATED;
    private static final String MOVIES_KEY = "movies";
    private static final String FAVORITE = "favorite";
    private static final String SORT_SETTING_KEY = "sort_setting";
    private String movieSortBy = POPULARITY_DESC;

    private static final String[] MOVIE_COLUMNS = {
            MovieContract.MovieEntry._ID,
            MovieContract.MovieEntry.COLUMN_MOVIE_ID,
            MovieContract.MovieEntry.COLUMN_TITLE,
            MovieContract.MovieEntry.COLUMN_IMAGE,
            MovieContract.MovieEntry.COLUMN_IMAGE2,
            MovieContract.MovieEntry.COLUMN_OVERVIEW,
            MovieContract.MovieEntry.COLUMN_RATING,
            MovieContract.MovieEntry.COLUMN_DATE
    };

    public static final int COL_ID = 0;
    public static final int COL_MOVIE_ID = 1;
    public static final int COL_TITLE = 2;
    public static final int COL_IMAGE = 3;
    public static final int COL_IMAGE2 = 4;
    public static final int COL_OVERVIEW = 5;
    public static final int COL_RATING = 6;
    public static final int COL_DATE = 7;
    private BottomNavigationView bottomNavigationView;
//    private BottomNavigationView bottomNavigationView;

    public MainFragment() {
        // Required empty public constructor
    }



    public interface Callback {
        void onItemSelected(MovieItem movie);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setHasOptionsMenu(true);

    }

  /*  @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_fragment_main, menu);
        MenuItem action_sort_by_popularity = menu.findItem(R.id.action_sort_by_popularity);
        MenuItem action_sort_by_rating = menu.findItem(R.id.action_sort_by_rating);
        MenuItem action_sort_by_favorite = menu.findItem(R.id.action_sort_by_favorite);


        if (movieSortBy.contentEquals(POPULARITY_DESC)) {
            if (!action_sort_by_popularity.isChecked()) {
                action_sort_by_popularity.setChecked(true);
            }
        } else if (movieSortBy.contentEquals(RATING_DESC)) {
            if (!action_sort_by_rating.isChecked()) {
                action_sort_by_rating.setChecked(true);
            }
        } else if (movieSortBy.contentEquals(FAVORITE)) {
            if (!action_sort_by_popularity.isChecked()) {
                action_sort_by_favorite.setChecked(true);
            }

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Log.e("onOptionsItemSelected",id+" is");
        switch (id) {
            case R.id.action_sort_by_popularity:
                if (item.isChecked()) {
                    item.setChecked(false);
                } else {
                    item.setChecked(true);
                }
                movieSortBy = POPULARITY_DESC;
                updateMovies(movieSortBy);
                return true;
            case R.id.action_sort_by_rating:
                if (item.isChecked()) {
                    item.setChecked(false);
                } else {
                    item.setChecked(true);
                }
                movieSortBy = RATING_DESC;
                updateMovies(movieSortBy);
                return true;
            case R.id.action_sort_by_favorite:
                if (item.isChecked()) {
                    item.setChecked(false);
                } else {
                    item.setChecked(true);
                }
                movieSortBy = FAVORITE;
                updateMovies(movieSortBy);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mNetHelper= NetHelper.getInstance(this.getActivity());
        View view=inflater.inflate(R.layout.fragment_main, container, false);


            bottomNavigationView = (BottomNavigationView)
                    getActivity().findViewById(R.id.navigation);
            bottomNavigationView.setOnNavigationItemSelectedListener
                    (new BottomNavigationView.OnNavigationItemSelectedListener() {
                        @Override
                        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                            int id = item.getItemId();
                            Log.e("onNavigationItemSelect",id+" is");
                            switch (id) {
                                case R.id.action_sort_by_popularity:
                                    if (item.isChecked()) {
                                        item.setChecked(false);
                                    } else {
                                        item.setChecked(true);
                                    }
                                    movieSortBy = Urls.SORT_BY_POPULAR;
                                    updateMovies(movieSortBy);
                                    return true;
                                case R.id.action_sort_by_rating:
                                    if (item.isChecked()) {
                                        item.setChecked(false);
                                    } else {
                                        item.setChecked(true);
                                    }
                                    movieSortBy = Urls.SORT_BY_TOP_RATED;
                                    updateMovies(movieSortBy);
                                    return true;
                                case R.id.action_sort_by_favorite:
                                    if (item.isChecked()) {
                                        item.setChecked(false);
                                    } else {
                                        item.setChecked(true);
                                    }
                                    movieSortBy = FAVORITE;
                                    updateMovies(movieSortBy);
                                    return true;



                            }
                            return true;
                        }
                    });


//        bottomNavigationView.setNavigationItemSelectedListener


        mContext=this.getActivity();
        gson=new Gson();
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        movieAdapter = new MovieAdapter(getActivity(),  new ArrayList<MovieItem>(), new ClickListener() {
            @Override
            public void onPositionClicked(int position) {
                MovieItem movie = movieAdapter.getItem(position);
                ((Callback) getActivity()).onItemSelected(movie);
            }
        });
        int mNoOfColumns = Utility.calculateNoOfColumns(getContext());
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), mNoOfColumns);
        recyclerView.setLayoutManager(mLayoutManager);
//                recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());


//                DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
//                        linearLayoutManager.getOrientation());
//                recyclerView.addItemDecoration(dividerItemDecoration);

        recyclerView.setAdapter(movieAdapter);
//        movieAdapter = new MovieAdapter(getActivity(), allItems, new ClickListener() {
//            @Override
//            public void onPositionClicked(int position) {
//                MovieItem movie = (MovieItem) allItems.get(position);
//                ((Callback) getActivity()).onItemSelected(movie);
//            }
//        });
        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(SORT_SETTING_KEY)) {
                movieSortBy = savedInstanceState.getString(SORT_SETTING_KEY);
                Log.e("movieSortBy",movieSortBy+" is");
            }

            if (savedInstanceState.containsKey(MOVIES_KEY)) {
                allItems = savedInstanceState.getParcelableArrayList(MOVIES_KEY);
                movieAdapter.setData(allItems);
            } else {
                updateMovies(movieSortBy);
            }
        } else {
            updateMovies(movieSortBy);
        }
        return view ;
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (!movieSortBy.contentEquals(POPULARITY_DESC)) {
            outState.putString(SORT_SETTING_KEY, movieSortBy);
            Log.e("putString",movieSortBy);
        }
        if (allItems != null) {
            outState.putParcelableArrayList(MOVIES_KEY, allItems);
        }
        super.onSaveInstanceState(outState);
    }



    void updateMovies(String sort_by)
    {
        if (!sort_by.contentEquals(FAVORITE)) {
            mNetHelper.fetchMovies(sort_by, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.e("Request Response", response);
                    JSONObject jsonObject = null;

                    try {
                        jsonObject = new JSONObject(response);
                        JSONArray resultsArr = jsonObject.getJSONArray("results");

                        allItems=new ArrayList<>();
                        //  allItems= JsonParser.getInstnace(mContext).fetchMovies(response);
                        allItems = gson.fromJson(String.valueOf(resultsArr), new TypeToken<List<MovieItem>>() {
                        }.getType());

                        if (allItems != null) {
                            if (movieAdapter != null) {
                                movieAdapter.setData(allItems);
                            }


                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("hello", error.toString() + " " + error.getMessage());
                    Toast.makeText(getActivity(), R.string.check_internet_connection, Toast.LENGTH_LONG).show();
                }
            });
        }
        else {
            new FetchFavoriteMoviesTask(getActivity()).execute();
        }
    }




    public class FetchFavoriteMoviesTask extends AsyncTask<Void, Void, List<MovieItem>> {

        private Context mContext;

        public FetchFavoriteMoviesTask(Context context) {
            mContext = context;
        }

        private List<MovieItem> getFavoriteMoviesDataFromCursor(Cursor cursor) {
            List<MovieItem> results = new ArrayList<>();
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    MovieItem movie = new MovieItem(cursor);
                    results.add(movie);
                } while (cursor.moveToNext());
                cursor.close();
            }
            return results;
        }

        @Override
        protected List<MovieItem> doInBackground(Void... params) {
            Cursor cursor = mContext.getContentResolver().query(
                    MovieContract.MovieEntry.CONTENT_URI,
                    MOVIE_COLUMNS,
                    null,
                    null,
                    null
            );
            return getFavoriteMoviesDataFromCursor(cursor);
        }

        @Override
        protected void onPostExecute(List<MovieItem> movies) {

            if (movies != null) {
                if (movieAdapter != null) {
                    movieAdapter.setData(movies);
                }
                allItems = new ArrayList<>();
                allItems.addAll(movies);
            }
        }
    }

}
