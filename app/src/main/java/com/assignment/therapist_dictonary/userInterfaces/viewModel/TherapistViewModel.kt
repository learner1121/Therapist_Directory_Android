package com.assignment.therapist_dictonary.userInterfaces.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.assignment.therapist_dictonary.data.model.Therapist
import com.assignment.therapist_dictonary.data.repository.TherapistRepository
import com.assignment.therapist_dictonary.roomDatabase.TherapistLocal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TherapistViewModel(private val repo: TherapistRepository) : ViewModel() {

    private val _therapist = MutableLiveData<List<Therapist>>()
    val therapist: LiveData<List<Therapist>> get() = _therapist

    fun getTherapist() {
        viewModelScope.launch {
            //  Load cached therapists from Room
            val cached = withContext(Dispatchers.IO) {
                repo.getLocalTherapist().also {
                    Log.d("LocalRepo", "Cached Therapist Count: ${it.size}")
                }
            }

            if (cached.isNotEmpty()) {
                // Show cached therapists
                _therapist.value = cached.map {
                    Therapist(
                        name = it.name,
                        specialization = it.specialization,
                        availability = it.availability,
                        about = it.about,
                        languages = it.language
                    )
                }
            } else {
                //  Fetch from API if cache is empty
                try {
                    val response = withContext(Dispatchers.IO) { repo.getTherapist() }
                    if (response.isSuccessful) {
                        response.body()?.let { list ->
                            _therapist.value = list

                            //  Save API data to Room on IO thread
                            withContext(Dispatchers.IO) {
                                list.forEach { therapist ->

                                    Log.d("RoomDebug", "Preparing to insert ${therapist.name}, languages: ${therapist.languages}")

                                    val localTherapist = TherapistLocal(
                                        name = therapist.name,
                                        specialization = therapist.specialization,
                                        availability = therapist.availability,
                                        about = therapist.about,
                                        language = therapist.languages ?: emptyList()

                                    )
                                    Log.d("RoomDebug", "Inserting therapist : ${localTherapist.name}")
                                    repo.addLocalTherapist(localTherapist)

                                }

                                //  Confirm DB contents
                                val updatedCache = repo.getLocalTherapist()
                                Log.d("RoomDebug", "DB now has ${updatedCache.size} therapists")
                            }
                        }
                    } else {
                        Log.e("TherapistViewModel", "API Error: ${response.code()}")
                    }
                } catch (e: Exception) {
                    Log.e("TherapistViewModel", "Exception: ${e.message}")
                    e.printStackTrace()
                }
            }
        }
    }
    private val _filteredTherapist = MutableLiveData<List<TherapistLocal>>()
    val filteredTherapist: LiveData<List<TherapistLocal>> get() = _filteredTherapist

    fun filterTherapists(specialization: String) {
        viewModelScope.launch {
            val response = withContext(Dispatchers.IO) {
                repo.filteredTherapist(specialization)
            }
            _filteredTherapist.value = response
        }
    }

}

class TherapistViewModelFactory(
    private val repo: TherapistRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TherapistViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TherapistViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
