package pt.greggz.myapplication.Model.POJOs;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class MovieResults {
    @SerializedName("results")
    public ArrayList<Movie> movies;
}
