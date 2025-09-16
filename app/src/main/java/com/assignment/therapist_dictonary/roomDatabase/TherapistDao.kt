package com.assignment.therapist_dictonary.roomDatabase


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.assignment.therapist_dictonary.data.model.Therapist
import okhttp3.Response

@Dao
interface TherapistDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addLocal(therapist: TherapistLocal)

    @Query("SELECT * FROM TherapistTable")
    suspend fun getLocal(): List<TherapistLocal>

    @Query("Select * From TherapistTable Where specialization = :specialization ")
    suspend fun filteredTherapists(specialization: String) : List<TherapistLocal>
}