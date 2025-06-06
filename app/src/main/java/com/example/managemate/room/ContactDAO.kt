package com.example.managemate.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

// Data Access Object (DAO): this interface defines methods to interact with the Room database
@Dao
interface ContactDAO {

    // suspend means this will run in the background (not on the main thread)
    @Insert
    suspend fun insertContact(contact: Contact): Long

    @Update
    suspend fun updateContact(contact: Contact)

    @Delete
    suspend fun deleteContact(contact: Contact)

    // @Query is used when we want to write our own SQL
    @Query("DELETE FROM contactTable")
    suspend fun deleteAll()

    // No suspend needed here because LiveData already works in the background
    @Query("SELECT * FROM contactTable")
    fun getAllContactsInDB(): LiveData<List<Contact>>
}
