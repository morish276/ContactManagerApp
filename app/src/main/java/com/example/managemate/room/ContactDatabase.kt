package com.example.managemate.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// This tells Room that this is the main database class
// and includes the list of all tables (entities)

@Database(entities = [Contact::class], version = 1)
abstract class ContactDatabase : RoomDatabase() {
    // This connects the DAO (interface with DB functions) to the database
    abstract val contactDAO : ContactDAO

    // Singleton Design Pattern: to make sure only ONE instance of the DB exists
    // avoiding unnecessary overhead associated with repeated database creation

    // Companion object = like static in Java
    // Allows us to create one shared instance of the database.
    companion object{
        // @Volatile ensures the latest value of INSTANCE is always visible to all threads
        @Volatile
        private var INSTANCE: ContactDatabase?= null

        // Returns the single (shared) instance of the database
        fun getInstance(context: Context): ContactDatabase {
            // synchronized block prevents multiple threads from creating multiple DB instances at once
            synchronized(this){
                var instance = INSTANCE

                // If INSTANCE is null, create the database
                if(instance == null){
                    // Creating the database object
                    instance = Room.databaseBuilder(
                        // Room doesn’t allow you to directly instantiate the DB using ContactDatabase(),
                        // because it needs to generate a lot of boilerplate code behind the scenes (based on your @Entity, @Dao, etc.)
                        // Room.databaseBuilder() is the official way to safely and correctly build DB instance

                        context.applicationContext, // use application context to avoid memory leaks
                        ContactDatabase::class.java, // tell Room which DB class to use
                        "contactsDB"  // name of the SQLite database file
                    ).build()
                }
                // Save the newly created instance to the INSTANCE variable for later use
                INSTANCE = instance
                // Return the single database instance
                return instance
            }
        }
    }
}