package com.bytebandits.sanjeevani

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.bytebandits.sanjeevani.screens.Home


@Composable
fun Navigation(modifier: Modifier, navHostController: NavHostController) {

    NavHost(navController = navHostController, startDestination = "home") {
        composable("home") {
            Home()
        }


    }

}