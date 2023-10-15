package com.ssu.moneymate.ui.main.property;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {PropertyData.class}, version = 1, exportSchema = false)
public abstract class PropertyDatabase extends RoomDatabase {
    public abstract PropertyDataDao propertyDataDao();
    private static PropertyDatabase instance;

    public static synchronized PropertyDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            PropertyDatabase.class, "property_database")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
