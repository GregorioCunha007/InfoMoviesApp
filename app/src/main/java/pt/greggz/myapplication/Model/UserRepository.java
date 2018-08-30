package pt.greggz.myapplication.Model;

import pt.greggz.myapplication.Model.persistence.AppDatabase;

public class UserRepository {

    private static UserRepository sInstance;
    private final AppDatabase mDatabase;

    private UserRepository(final AppDatabase database) {
        mDatabase = database;
        // Init specific data about the user here
    }

    public static UserRepository getInstance(final AppDatabase database) {
        if (sInstance == null) {
            synchronized (UserRepository.class) {
                if (sInstance == null) {
                    sInstance = new UserRepository(database);
                }
            }
        }
        return sInstance;
    }
}
