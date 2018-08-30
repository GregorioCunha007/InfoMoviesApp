package pt.greggz.myapplication.Model.DAOs;

import pt.greggz.myapplication.Model.POJOs.Movie;
import pt.greggz.myapplication.Model.POJOs.NowPlayingMovies;
import pt.greggz.myapplication.Model.POJOs.UpcomingMovies;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface MovieDao {

    @Query("Select * from Movie Inner join UpcomingMovies on Movie.id = UpcomingMovies.movie_id")
    LiveData<List<Movie>> loadUpcomingMovies();

    @Query("Select * from Movie Inner join NowPlayingMovies on Movie.id = NowPlayingMovies.movie_id")
    LiveData<List<Movie>> loadNowPlayingMovies();

    @Query("Select * from Movie")
    LiveData<List<Movie>> loadMovies();

    @Query("DELETE from Movie")
    void deleteAllMovies();

    // INSERTS

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long [] insertMovies(List<Movie> movies);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUpcomingMovies(List<UpcomingMovies> upcomingMovies);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertNowPlayingMovies(List<NowPlayingMovies> nowPlayingMovies);

}