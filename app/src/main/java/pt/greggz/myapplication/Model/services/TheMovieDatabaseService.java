package pt.greggz.myapplication.Model.services;

import pt.greggz.myapplication.Model.MovieRepository;
import pt.greggz.myapplication.Model.POJOs.Movie;
import java.util.List;

import pt.greggz.myapplication.Model.POJOs.MovieResults;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TheMovieDatabaseService {

    String HTTPS_API_MOVIEDATABASE_URL = "https://api.themoviedb.org/3/";

    @GET("movie/upcoming")
    Call<MovieResults> getUpcomingMovies(@Query(value = "api_key") String api_key);

    @GET("movie/now_playing")
    Call<MovieResults> getNowPlayingMovies(@Query(value = "api_key") String api_key);

    @GET("/movie/{id}/recommendations")
    Call<List<Movie>> getRecommendations(@Path("id") int movieId);
}
