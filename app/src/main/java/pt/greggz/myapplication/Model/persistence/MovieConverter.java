package pt.greggz.myapplication.Model.persistence;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;

import pt.greggz.myapplication.Model.POJOs.*;

public class MovieConverter {

    @TypeConverter
    public String movieToString(Movie movie) {
        return new Gson().toJson(movie);
    }

    @TypeConverter
    public Movie stringToMovie(String src) {
        return new Gson().fromJson(src, Movie.class);
    }
}
