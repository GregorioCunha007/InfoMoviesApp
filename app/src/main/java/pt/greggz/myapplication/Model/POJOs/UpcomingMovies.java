package pt.greggz.myapplication.Model.POJOs;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import pt.greggz.myapplication.Model.persistence.MovieConverter;


@Entity(
        foreignKeys = {
        @ForeignKey(
                entity = Movie.class,
                parentColumns = "id",
                childColumns = "movie_id",
                onDelete = ForeignKey.CASCADE)
        },
        indices = @Index("movie_id")
)
public class UpcomingMovies {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "movie_id")
    public int movieId;

    public UpcomingMovies(@NonNull int movieId) {
        this.movieId = movieId;
    }
}
