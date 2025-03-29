package com.bytebandits.sanjeevani

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberMultiplePermissionsState


@SuppressLint("PermissionLaunchedDuringComposition")
@OptIn(ExperimentalPermissionsApi::class)
@Composable
<<<<<<< HEAD
fun LocationPermissions(onPermissionGranted: () -> Unit,  onPermissionDenied: () -> Unit, resetTrigger: () -> Unit) {
=======
fun LocationPermissions(onPermissionGranted: () -> Unit) {
>>>>>>> 4e42eac (Added ICP and Frontend)
    val context = LocalContext.current
    val locationPermissionState = rememberMultiplePermissionsState(
        listOf(
            android.Manifest.permission.ACCESS_COARSE_LOCATION,
            android.Manifest.permission.ACCESS_FINE_LOCATION

        )
    )

    val permissionsGranted = locationPermissionState.permissions.any { it.status.isGranted }

    LaunchedEffect(locationPermissionState) {
        locationPermissionState.launchMultiplePermissionRequest()
    }

    when {
        locationPermissionState.allPermissionsGranted -> {
            onPermissionGranted()
        }
        locationPermissionState.shouldShowRationale -> {
<<<<<<< HEAD
            onPermissionDenied()
=======
>>>>>>> 4e42eac (Added ICP and Frontend)
            Toast.makeText(context, "Location permission is required to find nearby hospitals", Toast.LENGTH_LONG).show()
        }

    }
<<<<<<< HEAD
    resetTrigger()
=======
>>>>>>> 4e42eac (Added ICP and Frontend)
    }

