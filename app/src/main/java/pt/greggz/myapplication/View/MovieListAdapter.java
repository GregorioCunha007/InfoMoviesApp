package pt.greggz.myapplication.View;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import pt.greggz.myapplication.ImageLoader;
import pt.greggz.myapplication.Model.POJOs.Movie;
import pt.greggz.myapplication.R;

public class MovieListAdapter extends ArrayAdapter<Movie> {

    private ArrayList<Movie> movies;
    private Context mContext;
    private ImageView thumbnail;
    private TextView title;


    public MovieListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Movie> movies) {
        super(context, resource, movies);
        this.movies = movies;
        this.mContext = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // return super.getView(position, convertView, parent);

        Movie movie = getItem(position);

        if(convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_movie_list, parent, false);
        }

        thumbnail = convertView.findViewById(R.id.film_cover);
        title = convertView.findViewById(R.id.movieTitle);

        new ImageLoader(movie.posterPath, thumbnail, mContext).execute();

        title.setText(movie.title);

        return convertView;
    }
}
