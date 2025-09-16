package com.assignment.therapist_dictonary.userInterfaces.view

import android.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun MainScreen(navController: NavController){

    Column (Modifier
        .fillMaxSize()
        .padding(top = 32.dp, start = 8.dp, end = 8.dp)){

        Row(Modifier.fillMaxWidth()
            .padding(8.dp),
            horizontalArrangement = Arrangement.Center) { Text("Therapist App", style = MaterialTheme.typography.displayMedium) }
        Row(Modifier.fillMaxWidth()) {
                Box(
                    Modifier
                        .height(100.dp)
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.primaryContainer)
                        .clickable(
                            onClick = { navController.navigate("home") }
                        ),
                    contentAlignment = Alignment.Center) {
                    Text("Home")
                }
            }
        Spacer(Modifier.height(8.dp))
        Row(Modifier.fillMaxWidth())  {
            Box(
                Modifier
                    .height(100.dp)
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .fillMaxWidth()
                    .clickable(
                        onClick = { navController.navigate("filterScreen") }
                    ),
                contentAlignment = Alignment.Center) {
                Text("Search Therapist")
            }
        }

    }

}