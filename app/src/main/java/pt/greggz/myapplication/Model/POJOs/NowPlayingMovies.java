package pt.greggz.myapplication.Model.POJOs;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

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
public class NowPlayingMovies {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "movie_id")
    public int movieId;

    public NowPlayingMovies(@NonNull int movieId) {
        this.movieId = movieId;
    }
}
