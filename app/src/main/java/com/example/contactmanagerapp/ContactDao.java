package com.example.contactmanagerapp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

//Data Access Object
@Dao
public interface ContactDao {
    @Insert
    void insert(Contact contact);
    @Delete
    void delete(Contact contact);
    @Query("SELECT * FROM contacts_table")
    LiveData<List<Contact>> getAllContact();
}
