package pt.greggz.myapplication.Model.persistence;


import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

import pt.greggz.myapplication.Model.DAOs.MovieDao;
import pt.greggz.myapplication.Model.DAOs.UserDao;
import pt.greggz.myapplication.Model.POJOs.Movie;
import pt.greggz.myapplication.Model.POJOs.NowPlayingMovies;
import pt.greggz.myapplication.Model.POJOs.UpcomingMovies;
import pt.greggz.myapplication.Model.POJOs.User;

@Database(entities = {User.class , Movie.class, UpcomingMovies.class, NowPlayingMovies.class } , version = 15, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    @VisibleForTesting
    public static final String DATABASE_NAME = "basic-sample-db";

    public abstract MovieDao movieModel();

    public abstract UserDao userModel();

    private final MutableLiveData<Boolean> mIsDatabaseCreated = new MutableLiveData<>();

    public static AppDatabase getInstance(Context context) {
        if(INSTANCE == null) {
            synchronized (AppDatabase.class){
                INSTANCE =
                        buildDatabase(context.getApplicationContext());
                }
        }

        return INSTANCE;
    }

    private static AppDatabase buildDatabase(final Context applicationContext) {
        return Room.databaseBuilder(applicationContext, AppDatabase.class, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build();
    }


}
