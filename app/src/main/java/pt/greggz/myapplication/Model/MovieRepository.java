package pt.greggz.myapplication.Model;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import pt.greggz.myapplication.Model.DAOs.MovieDao;
import pt.greggz.myapplication.Model.POJOs.*;
import pt.greggz.myapplication.Model.persistence.AppDatabase;
import pt.greggz.myapplication.Model.services.ApiClient;
import pt.greggz.myapplication.Model.services.TheMovieDatabaseService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class MovieRepository {

    private static MovieRepository sInstance;
    private final MovieDao movieDao;
    private TheMovieDatabaseService webService;

    private LiveData<List<UpcomingMovies>> upcomingMovieList;

    public static MovieRepository getsInstance(Application context) {
        if(sInstance == null) {
            sInstance = new MovieRepository(context);
        }
        return sInstance;
    }

    private MovieRepository(Application context) {
        this.movieDao = AppDatabase.getInstance(context).movieModel();
        this.webService = ApiClient.getClient().create(TheMovieDatabaseService.class);
    }

    public LiveData<List<Movie>> getUpcomingMovies(){
        if(movieDao.loadUpcomingMovies().getValue() == null) {
            webService.getUpcomingMovies("db8128311eaaeb987b6a836ae1915e56").
                    enqueue(new Callback<MovieResults>() {
                        @Override
                        public void onResponse(Call<MovieResults> call, Response<MovieResults> response) {
                            MovieResults results = response.body();
                            if(results == null) return;
                            new insertAsyncTask(movieDao,"Upcoming").execute(results.movies.toArray(new Movie[results.movies.size()]));
                        }

                        @Override
                        public void onFailure(Call<MovieResults> call, Throwable t) {
                            Log.d("Retrofit error", t.getMessage());
                        }
                    });
        }
        return movieDao.loadUpcomingMovies();
    }

    public LiveData<List<Movie>> getNowPlayingMovies(){
        if(movieDao.loadNowPlayingMovies().getValue() == null) {
            webService.getNowPlayingMovies("db8128311eaaeb987b6a836ae1915e56").
                    enqueue(new Callback<MovieResults>() {
                        @Override
                        public void onResponse(Call<MovieResults> call, Response<MovieResults> response) {
                            MovieResults results = response.body();
                            if(results == null) return;
                            new insertAsyncTask(movieDao,"NowPlaying").execute(results.movies.toArray(new Movie[results.movies.size()]));
                        }

                        @Override
                        public void onFailure(Call<MovieResults> call, Throwable t) {
                            Log.d("Retrofit error", t.getMessage());
                        }
                    });
        }
        return movieDao.loadNowPlayingMovies();
    }

//    public LiveData<List<Movie>> loadMovies() {
//        return movieDao.loadMovies();
//    }
//
//     public void deleteAll() {
//        new deleteAsyncTask(movieDao).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR , null);
//     }

    private static class insertAsyncTask extends AsyncTask<Movie, Void, Void> {

        private final MovieDao mAsyncTask;
        private final String mEntity;

        insertAsyncTask(MovieDao dao, String entity){
            mEntity = entity;
            mAsyncTask = dao;
        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        protected Void doInBackground(Movie... movies) {
            mAsyncTask.insertMovies(Arrays.asList(movies));
            switch (mEntity) {
                case "Upcoming":
                        mAsyncTask.insertUpcomingMovies(moviesIdToUpcoming(movies));
                    break;
                case "NowPlaying":
                        mAsyncTask.insertNowPlayingMovies(moviesIdToNowPlaying(movies));
                    break;
            }

            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<Void,Void,Void> {

        private final MovieDao mAsyncTask;

        deleteAsyncTask(MovieDao dao){
            mAsyncTask = dao;
        }

        @Override
        protected void onPreExecute() {
            Log.d("Pre","ssssssssssssssssss");
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mAsyncTask.deleteAllMovies();
            return null;
        }
    }

    private static List<UpcomingMovies> moviesIdToUpcoming(Movie ... movies) {
        ArrayList<UpcomingMovies> upcomingMovies = new ArrayList<>(movies.length);
        int i = 0;
        for(Movie m : movies) {
            upcomingMovies.add(new UpcomingMovies(m.id));
        }
        return upcomingMovies;
    }

    private static List<NowPlayingMovies> moviesIdToNowPlaying(Movie ... movies) {
        ArrayList<NowPlayingMovies> nowPlayingMovies = new ArrayList<>(movies.length);
        int i = 0;
        for(Movie m : movies) {
            nowPlayingMovies.add(new NowPlayingMovies(m.id));
        }
        return nowPlayingMovies;
    }
}
