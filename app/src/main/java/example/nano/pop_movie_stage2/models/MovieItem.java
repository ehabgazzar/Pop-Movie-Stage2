package example.nano.pop_movie_stage2.models;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import example.nano.pop_movie_stage2.MainFragment;

/**
 * Created by ehab- on 4/16/2017.
 */

public class MovieItem implements Parcelable {
    private String id;
   private String title;
    private String backdrop_path;
    private String poster_path;
    private String overview;
    private String vote_average;
    private String release_date;








    public String getVote_average() {
        return vote_average;
    }

    public void setVote_average(String vote_average) {
        this.vote_average = vote_average;
    }
public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String url) {

        this.backdrop_path = url;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

        public MovieItem()
        {

        }
    public MovieItem(Cursor cursor) {
        this.id = cursor.getString(MainFragment.COL_MOVIE_ID);
        this.title = cursor.getString(MainFragment.COL_TITLE);
        this.poster_path = cursor.getString(MainFragment.COL_IMAGE);
        this.backdrop_path = cursor.getString(MainFragment.COL_IMAGE2);
        this.overview = cursor.getString(MainFragment.COL_OVERVIEW);
        this.vote_average = cursor.getString(MainFragment.COL_RATING);
        this.release_date = cursor.getString(MainFragment.COL_DATE);
    }



    public String getId() {
        return id;
    }

    protected MovieItem(Parcel in) {
        id = in.readString();
        title = in.readString();
        poster_path = in.readString();
        backdrop_path = in.readString();
        overview = in.readString();
        vote_average = in.readString();
        release_date = in.readString();
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(title);
        dest.writeString(poster_path);
        dest.writeString(backdrop_path);
        dest.writeString(overview);
        dest.writeString(vote_average);
        dest.writeString(release_date);
    }

    public static final Parcelable.Creator<MovieItem> CREATOR
            = new Parcelable.Creator<MovieItem>() {
        public MovieItem createFromParcel(Parcel in) {
            return new MovieItem(in);
        }

        public MovieItem[] newArray(int size) {
            return new MovieItem[size];
        }
    };


}
