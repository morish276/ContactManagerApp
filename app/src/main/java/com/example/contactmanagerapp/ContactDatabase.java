package com.example.contactmanagerapp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Contact.class},version = 1)
public abstract class ContactDatabase extends RoomDatabase {
    // Abstract method to get the DAO
    public abstract ContactDao getContactDao();

    // Singleton pattern
    private static ContactDatabase dbInstance;

    // Synchronized method to ensure a single instance
    public static synchronized ContactDatabase getInstance(Context context){
        if (dbInstance == null){
            dbInstance = Room.databaseBuilder(context.getApplicationContext(),ContactDatabase.class,"contacts_db").fallbackToDestructiveMigration().build();
        }
        return dbInstance;
    }
}
