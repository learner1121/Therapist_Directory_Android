package com.assignment.therapist_dictonary.data.repository

import android.util.Log
import com.assignment.therapist_dictonary.data.remote.TherapistApi
import com.assignment.therapist_dictonary.roomDatabase.TherapistDao
import com.assignment.therapist_dictonary.roomDatabase.TherapistLocal

class TherapistRepository(
    private val api: TherapistApi,
    private val dao: TherapistDao
){
    suspend fun getTherapist() = api.getDetails()

    suspend fun getLocalTherapist(): List<TherapistLocal>{
        return dao.getLocal()
    }

    suspend fun addLocalTherapist(localTherapist : TherapistLocal){
        Log.d("RoomDebug","Inserting therapist : ${localTherapist.name}")
        dao.addLocal(localTherapist)
    }

    suspend fun filteredTherapist(specialization: String): List<TherapistLocal>{
        Log.d("Filtered Therapists","Getting Therapists from Local: ${specialization}")
        return dao.filteredTherapists(specialization)
    }
}