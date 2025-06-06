package com.example.managemate.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// This data class defines a table in the Room database.
// Each instance of this class represents a row in the "contactTable".
@Entity(tableName = "contactTable")
data class Contact(

    // Primary key for each contact. It's auto-generated.
    @PrimaryKey(autoGenerate = true)
    var contactId: Int = 0,

    // This column will be stored as "contactName" in the database.
    // Helpful if you want a different name in the DB than the variable name.
    @ColumnInfo(name = "contactName")
    var contactName: String,

    // This column will be stored with the same name as the variable (contactEmail).
    var contactEmail: String
)