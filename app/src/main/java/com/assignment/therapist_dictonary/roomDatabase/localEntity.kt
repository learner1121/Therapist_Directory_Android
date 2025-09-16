package com.assignment.therapist_dictonary.roomDatabase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TherapistTable")
data class TherapistLocal(
    @PrimaryKey (autoGenerate = true) val id: Int = 0,
    val name: String,
    val specialization: String,
    val availability: Boolean,
    val about: String,
    val language: List<String>
)