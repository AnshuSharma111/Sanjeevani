package com.bytebandits.sanjeevani

import androidx.compose.runtime.Composable
<<<<<<< HEAD
import androidx.compose.runtime.internal.composableLambda
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
=======
import androidx.compose.ui.Modifier
>>>>>>> 4e42eac (Added ICP and Frontend)
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.bytebandits.sanjeevani.screens.Home
<<<<<<< HEAD
import com.bytebandits.sanjeevani.screens.HospitalSpecificDetailsScreen
import com.bytebandits.sanjeevani.screens.SearchResults
import com.bytebandits.sanjeevani.viewmodels.SearchViewModel
=======
>>>>>>> 4e42eac (Added ICP and Frontend)


@Composable
fun Navigation(modifier: Modifier, navHostController: NavHostController) {

<<<<<<< HEAD
    val searchViewModel: SearchViewModel = hiltViewModel()

    NavHost(navController = navHostController, startDestination = "home") {
        composable("home") {
            Home(navHostController, searchViewModel)
        }

        composable("searchResults") {
            SearchResults(searchViewModel)
        }

        composable("HospitalSpecificDetailsScreen"){
            HospitalSpecificDetailsScreen()
        }
=======
    NavHost(navController = navHostController, startDestination = "home") {
        composable("home") {
            Home()
        }

>>>>>>> 4e42eac (Added ICP and Frontend)

    }

}