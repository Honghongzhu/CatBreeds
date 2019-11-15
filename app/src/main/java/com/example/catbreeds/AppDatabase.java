package com.example.catbreeds;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {CatBreed.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CatDao catDao();

    private static AppDatabase instance;

    public static AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, AppDatabase.class, "catDb")
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
}
