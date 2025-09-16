package com.assignment.therapist_dictonary.userInterfaces.view

import android.icu.text.SimpleDateFormat
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import java.util.Date
import java.util.Locale
@Composable
fun BookSessionScreen(navController: NavController, name: String) {
    var showForm by remember { mutableStateOf(false) }
    var selectedDate by remember { mutableStateOf("") }
    var selectedTime by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 32.dp, start = 8.dp, end = 8.dp, bottom = 8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Book Session with $name",
            fontSize = 22.sp,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            shape = RoundedCornerShape(12.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Button(
                    onClick = { showForm = true },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Start Booking")
                }

                if (showForm) {
                    Spacer(modifier = Modifier.height(12.dp))

                    // Date selection
                    OutlinedTextField(
                        value = selectedDate,
                        onValueChange = { selectedDate = it },
                        placeholder = { Text("Select Date (dd/MM/yyyy)") },
                        modifier = Modifier.fillMaxWidth(),
                        readOnly = true,
                        trailingIcon = {
                            IconButton(onClick = {
                                val date = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())
                                selectedDate = date
                            }) {
                                Text("📅")
                            }
                        }
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Time selection
                    OutlinedTextField(
                        value = selectedTime,
                        onValueChange = { selectedTime = it },
                        placeholder = { Text("Select Time (HH:mm)") },
                        modifier = Modifier.fillMaxWidth(),
                        readOnly = true,
                        trailingIcon = {
                            IconButton(onClick = {
                                val time = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())
                                selectedTime = time
                            }) {
                                Text("⏰")
                            }
                        }
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = {
                            if (selectedDate.isEmpty() || selectedTime.isEmpty()) {
                                Toast.makeText(context, "Please select date & time", Toast.LENGTH_SHORT).show()
                                return@Button
                            }
                            Toast.makeText(
                                context,
                                "Session booked with $name on $selectedDate at $selectedTime",
                                Toast.LENGTH_LONG
                            ).show()
                            showForm = false
                            selectedDate = ""
                            selectedTime = ""
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Confirm Booking")
                    }
                }
            }
        }
    }
}
