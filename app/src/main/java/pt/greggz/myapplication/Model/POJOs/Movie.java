package pt.greggz.myapplication.Model.POJOs;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

@Entity
public class Movie {

    @PrimaryKey
    @NonNull
    @SerializedName("id")
    public int id;

    @ColumnInfo
    @SerializedName("name")
    public String name;

    @ColumnInfo
    @SerializedName("title")
    public String title;

    @ColumnInfo
    @SerializedName("poster_path")
    public String posterPath;
}
