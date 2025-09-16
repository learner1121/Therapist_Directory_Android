package com.assignment.therapist_dictonary

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.assignment.therapist_dictonary.navigation.AppNavHost
import com.assignment.therapist_dictonary.ui.theme.Therapist_DictonaryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Therapist_DictonaryTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                 start(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@SuppressLint("SuspiciousIndentation")
@Composable
fun start(modifier: Modifier) {
 val navController = rememberNavController()
    AppNavHost(navController)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Therapist_DictonaryTheme {

    }
}