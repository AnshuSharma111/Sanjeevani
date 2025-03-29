<<<<<<< HEAD
package com.bytebandits.sanjeevani

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bytebandits.sanjeevani.ui.theme.AarogyaSangamTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private  lateinit var navHostController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        WindowCompat.getInsetsController(window, window.decorView).isAppearanceLightStatusBars =
            true
        setContent {
            AarogyaSangamTheme {
                navHostController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize().padding(10.dp)) { innerPadding ->
                        Navigation(modifier = Modifier.padding(innerPadding), navHostController = navHostController)
                }
            }
        }
    }
}

=======
package com.bytebandits.sanjeevani

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bytebandits.sanjeevani.ui.theme.AarogyaSangamTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private  lateinit var navHostController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        WindowCompat.getInsetsController(window, window.decorView).isAppearanceLightStatusBars =
            true
        setContent {
            AarogyaSangamTheme {
                navHostController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize().padding(10.dp)) { innerPadding ->
                        Navigation(modifier = Modifier.padding(innerPadding), navHostController = navHostController)
                }
            }
        }
    }
}

>>>>>>> 4e42eac (Added ICP and Frontend)
