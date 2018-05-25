package example.nano.pop_movie_stage2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;
import java.util.List;

import example.nano.pop_movie_stage2.models.MovieItem;

/**
 * Created by ehab- on 4/16/2017.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {


    private final Context moiveContext;
    private final LayoutInflater movieInflater;
    private  ClickListener listener;
    private final MovieItem movie = new MovieItem();

    private List<MovieItem> movieObjects;

    public MovieAdapter(Context context, List<MovieItem> objects, ClickListener listener) {
        moiveContext = context;
        movieInflater = LayoutInflater.from(context);
        movieObjects = objects;
        this.listener=listener;
    }

    public Context getContext() {
        return moiveContext;
    }

    public void add(MovieItem object) {
            movieObjects.add(object);
      }



    public void clear() {

            movieObjects.clear();

        notifyDataSetChanged();
    }


    public void setData(List<MovieItem> data) {
        clear();
        for (MovieItem movie : data) {
            add(movie);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = movieInflater.inflate(R.layout.movie_item, parent, false);
        final MovieAdapter.ViewHolder viewHolder = new MovieAdapter.ViewHolder(view, listener);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final MovieItem movie = (MovieItem) movieObjects.get(position);

        String image_url = "http://image.tmdb.org/t/p/w185" + movie.getPoster_path();
          Picasso.get().load(image_url).fit().into(holder.imageView);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return movieObjects.size();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private WeakReference<ClickListener> listenerRef;
        public final ImageView imageView;


        public ViewHolder(View view,ClickListener listener) {
            super(view);
            listenerRef = new WeakReference<>(listener);

            imageView = (ImageView) view.findViewById(R.id.grid_item_image);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listenerRef.get().onPositionClicked(getAdapterPosition());
        }
    }
}
