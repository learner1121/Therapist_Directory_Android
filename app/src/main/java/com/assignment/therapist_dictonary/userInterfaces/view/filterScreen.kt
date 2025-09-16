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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.assignment.therapist_dictonary.userInterfaces.viewModel.TherapistViewModel

@Composable
fun FilterScreen(navController: NavController, viewModel: TherapistViewModel) {
    var expanded by remember { mutableStateOf(false) }
    var selectedSpecialization by remember { mutableStateOf("Select Specialization") }

    val filteredTherapist by viewModel.filteredTherapist.observeAsState(emptyList())

    LaunchedEffect(selectedSpecialization) {
        if (selectedSpecialization != "Select Specialization") {
            viewModel.filterTherapists(selectedSpecialization)
        }
    }

    Column(
        Modifier
            .fillMaxSize()
            .padding(top = 32.dp, start = 8.dp, end = 8.dp, bottom = 8.dp)
    ) {
        Row(Modifier.fillMaxWidth()
            .background(MaterialTheme.colorScheme.primaryContainer)) {
            Text(
                "Choose Therapist",
                style = MaterialTheme.typography.displaySmall,
            )
        }

        Spacer(Modifier.height(8.dp))

        // Wrap with Box to position DropdownMenu correctly
        Box {
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text(
                    selectedSpecialization,
                    Modifier
                        .weight(1f)
                        .padding(start = 8.dp, top = 8.dp),
                    style = MaterialTheme.typography.titleLarge
                )
                IconButton(onClick = { expanded = true }) {
                    Icon(Icons.Filled.KeyboardArrowDown, contentDescription = "Select")
                }
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                DropdownMenuItem(onClick = {
                    selectedSpecialization = "Speech Therapist"
                    expanded = false
                }, text = { Text("Speech Therapist") })

                DropdownMenuItem(onClick = {
                    selectedSpecialization = "Occupational Therapist"
                    expanded = false
                }, text = { Text("Occupational Therapist") })

                DropdownMenuItem(onClick = {
                    selectedSpecialization = "Nutritionist"
                    expanded = false
                }, text = { Text("Nutritionist") })
            }
        }

        Spacer(Modifier.height(16.dp))

        Text("Selected: $selectedSpecialization", style = MaterialTheme.typography.titleLarge)

        Spacer(Modifier.height(8.dp))

        LazyColumn {
            items(filteredTherapist) {
                therapist ->
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
                        Text(text = "Languages: ${therapist.language.joinToString(", ")}")
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
