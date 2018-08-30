package pt.greggz.myapplication.ViewModel;
import pt.greggz.myapplication.Model.MovieRepository;
import pt.greggz.myapplication.Model.POJOs.*;
import pt.greggz.myapplication.Model.persistence.AppDatabase;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class UpcomingMoviesViewModel extends AndroidViewModel {

    private LiveData<List<Movie>> mUpcomingMovies;
    private MovieRepository movieRepository;

    public UpcomingMoviesViewModel(@NonNull Application application ) {
        super(application);
        movieRepository = MovieRepository.getsInstance(application);
    }

    public LiveData<List<Movie>> getUpcomingMovies() {
        mUpcomingMovies = movieRepository.getUpcomingMovies();
        return mUpcomingMovies;
    }

}
