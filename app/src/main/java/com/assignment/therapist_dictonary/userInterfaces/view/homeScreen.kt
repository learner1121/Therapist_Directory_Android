package com.assignment.therapist_dictonary.userInterfaces.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.assignment.therapist_dictonary.userInterfaces.viewModel.TherapistViewModel

@Composable
fun homeScreen(viewModel: TherapistViewModel = viewModel(), navController: NavController) {
    var searchText by remember { mutableStateOf("") }

    val therapistList by viewModel.therapist.observeAsState(initial = emptyList())

    LaunchedEffect(Unit) {
        viewModel.getTherapist()
    }

    Column(
        Modifier
            .fillMaxSize()
            .padding(top = 32.dp, start = 8.dp, end = 8.dp, bottom = 8.dp)
    ) {

        Row(Modifier.fillMaxWidth()
            .background(MaterialTheme.colorScheme.primaryContainer)) { Text("List Of Therapists",
            style = MaterialTheme.typography.displaySmall,
            ) }

        if (therapistList.isEmpty()){
            Box(Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center){
                CircularProgressIndicator()
            }
        }else
        {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(items = therapistList) { therapist ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 6.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                        shape = CardDefaults.outlinedShape
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {

                            Row(
                                Modifier.fillMaxWidth(),
                                verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
                            ) {
                                Text(
                                    text = therapist.name,
                                    modifier = Modifier.weight(1f),
                                    color = Color.Black,
                                    style = MaterialTheme.typography.titleLarge
                                )
                                Box(
                                    modifier = Modifier
                                        .size(12.dp)
                                        .background(
                                            color = if (therapist.availability) Color.Green else Color.Gray,
                                            shape = CircleShape
                                        )
                                )
                            }

                            Spacer(modifier = Modifier.height(6.dp))
                            Text(text = "Specialization: ${therapist.specialization}")
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(text = "About: ${therapist.about}")
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(text = "Languages: ${therapist.languages?.joinToString(", ") ?: "Not specified"}")
                            Spacer(modifier = Modifier.height(4.dp))
                            Button(onClick = {
                                navController.navigate("BookSession/${therapist.name}")
                            }) {
                                Text("Book Session with ${therapist.name}")
                            }
                        }
                    }
                }
            }
        }
    }
}
