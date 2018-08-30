package pt.greggz.myapplication.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import pt.greggz.myapplication.Model.MovieRepository;
import pt.greggz.myapplication.Model.POJOs.*;

public class NowPlayingViewModel extends AndroidViewModel   {

    private final MovieRepository movieRepository;
    private LiveData<List<Movie>> nowPlaying;

    public NowPlayingViewModel(@NonNull Application application) {
        super(application);
        movieRepository = MovieRepository.getsInstance(application);
    }

    public LiveData<List<Movie>> getNowPlayingMovies() {
        nowPlaying = movieRepository.getNowPlayingMovies();
        return nowPlaying;
    }

}
