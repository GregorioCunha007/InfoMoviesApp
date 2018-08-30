package pt.greggz.myapplication.View;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import pt.greggz.myapplication.Model.POJOs.Movie;
import pt.greggz.myapplication.R;
import pt.greggz.myapplication.ViewModel.NowPlayingViewModel;

public class NowPlayingMoviesFragment extends Fragment {

    private ListView mUpcomingMovies;
    private ArrayList<Movie> moviesArrayList;
    private Context mContext;
    private NowPlayingViewModel viewModel;
    private MovieListAdapter movieListAdapter;

    public NowPlayingMoviesFragment() {
        // Required empty public constructor
    }

    public static NowPlayingMoviesFragment newInstance() {
        NowPlayingMoviesFragment fragment = new NowPlayingMoviesFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(NowPlayingViewModel.class);
       // getLifecycle().addObserver(viewModel);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_upcoming_movies, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUpcomingMovies = view.findViewById(R.id.upcomingMovies);
        subscribeToObservers();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onDetach() {
        mContext = null;
        super.onDetach();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void subscribeToObservers() {
        viewModel.getNowPlayingMovies().observe(this, nowPlayingMovies -> {
            // LOGS
            if(nowPlayingMovies == null)return;
            Log.d("Observer", "Here and upcomingmovies size is " + nowPlayingMovies.size());
            for(Movie movie : nowPlayingMovies) {
                Log.d("Upcoming", "Movie - " + movie.title);
            };
            // LOGIC
            if(movieListAdapter == null) {
                moviesArrayList = (ArrayList<Movie>) nowPlayingMovies;
                movieListAdapter = new MovieListAdapter(mContext,R.layout.item_movie_list, moviesArrayList);
                mUpcomingMovies.setAdapter(movieListAdapter);
            }else{
                moviesArrayList.clear();
                moviesArrayList.addAll(nowPlayingMovies);
                movieListAdapter.notifyDataSetChanged();
            }
        });
    }
}
