package com.assignment.therapist_dictonary.data.remote


import com.assignment.therapist_dictonary.data.model.Therapist
import retrofit2.Response
import retrofit2.http.GET

interface TherapistApi{

    @GET("/therapists")
    suspend fun getDetails(): Response<List<Therapist>>
}