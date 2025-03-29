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
fun LocationPermissions(onPermissionGranted: () -> Unit,  onPermissionDenied: () -> Unit, resetTrigger: () -> Unit) {
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
            onPermissionDenied()
            Toast.makeText(context, "Location permission is required to find nearby hospitals", Toast.LENGTH_LONG).show()
        }

    }
    resetTrigger()
    }

