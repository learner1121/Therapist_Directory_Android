package com.assignment.therapist_dictonary.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.assignment.therapist_dictonary.data.remote.RetrofitInstance
import com.assignment.therapist_dictonary.userInterfaces.view.homeScreen
import com.assignment.therapist_dictonary.userInterfaces.viewModel.TherapistViewModel
import com.assignment.therapist_dictonary.userInterfaces.viewModel.TherapistViewModelFactory
import com.assignment.therapist_dictonary.data.repository.TherapistRepository
import com.assignment.therapist_dictonary.roomDatabase.TherapistDb
import com.assignment.therapist_dictonary.userInterfaces.view.BookSessionScreen
import com.assignment.therapist_dictonary.userInterfaces.view.FilterScreen
import com.assignment.therapist_dictonary.userInterfaces.view.MainScreen



@Composable
fun AppNavHost(navController: NavHostController) {

    val context = LocalContext.current
    val dao = TherapistDb.getDatabase(context).therapistDao()
    val api = RetrofitInstance.api
    val repo = TherapistRepository(api,dao)
    val therapistViewModel: TherapistViewModel = viewModel(
        factory = TherapistViewModelFactory(repo)
    )
    NavHost(navController,"MainScreen"){
        composable("home"){
            homeScreen(viewModel = therapistViewModel, navController)
        }

        composable("filterScreen") { FilterScreen(navController,therapistViewModel) }
        composable("MainScreen") { MainScreen(navController) }

        composable(
            route = "BookSession/{therapistName}",
            arguments = listOf(navArgument("therapistName") { type = NavType.StringType })
        ) { backStackEntry ->
            val therapistName = backStackEntry.arguments?.getString("therapistName") ?: ""
            BookSessionScreen(navController, therapistName)
        }
    }

}


