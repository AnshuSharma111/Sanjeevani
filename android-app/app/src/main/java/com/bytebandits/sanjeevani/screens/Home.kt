package com.bytebandits.sanjeevani.screens

<<<<<<< HEAD
import android.widget.Toast
=======
>>>>>>> 4e42eac (Added ICP and Frontend)
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
<<<<<<< HEAD
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
=======
import androidx.compose.runtime.getValue
>>>>>>> 4e42eac (Added ICP and Frontend)
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
<<<<<<< HEAD
import androidx.compose.ui.platform.LocalContext
=======
>>>>>>> 4e42eac (Added ICP and Frontend)
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
<<<<<<< HEAD
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
=======
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
>>>>>>> 4e42eac (Added ICP and Frontend)
import com.airbnb.lottie.compose.rememberLottieComposition
import com.bytebandits.sanjeevani.LocationPermissions
import com.bytebandits.sanjeevani.R
import com.bytebandits.sanjeevani.ui.theme.Poppins
import com.bytebandits.sanjeevani.viewmodels.SearchViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
<<<<<<< HEAD
fun Home(navHostController: NavHostController, searchViewModel: SearchViewModel, modifier: Modifier = Modifier) {
    val radiusOptions = listOf("2", "5", "10")
=======
fun Home(modifier: Modifier = Modifier) {
    val radiusOptions = listOf("2 km", "5 km", "10 km")
>>>>>>> 4e42eac (Added ICP and Frontend)
    val hospitalOptions = listOf("General", "Special")
    var radiusExpanded by remember { mutableStateOf(false) }
    var hospitalExpanded by remember { mutableStateOf(false) }
    var radiusSelected by remember { mutableStateOf(radiusOptions[1]) }
<<<<<<< HEAD
    var finalRadius = radiusSelected.toInt()*1000
=======
>>>>>>> 4e42eac (Added ICP and Frontend)
    var hospitalSelected by remember { mutableStateOf(hospitalOptions[1]) }

    var showPermissionDialog by remember { mutableStateOf(false) }

    val loading by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.global_location))
<<<<<<< HEAD
    var animation by remember { mutableStateOf(false) }


    val hospitalsList by searchViewModel.hospitalList.observeAsState(emptyList())

    val context = LocalContext.current
=======

    val searchViewModel: SearchViewModel = hiltViewModel()
>>>>>>> 4e42eac (Added ICP and Frontend)

    Box(modifier = modifier
        .fillMaxSize()
        .padding(20.dp)) {
        Column(modifier = Modifier.offset(y = (50).dp)) {

            Text(
<<<<<<< HEAD
                "Sanjeevani",
=======
                "Aarogya Sangam",
>>>>>>> 4e42eac (Added ICP and Frontend)
                fontFamily = Poppins,
                fontWeight = FontWeight.SemiBold,
                fontSize = 24.sp,
                modifier = Modifier.padding(bottom = 30.dp)
            )
            Card(
<<<<<<< HEAD
                colors = CardDefaults.elevatedCardColors(Color(0xFF99E5C9)),
=======
                colors = CardDefaults.elevatedCardColors(Color(0xFF7FFFD4)),
>>>>>>> 4e42eac (Added ICP and Frontend)
                modifier = Modifier,
                shape = RoundedCornerShape(20.dp),
                elevation = CardDefaults.elevatedCardElevation(10.dp)
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = modifier
                        .fillMaxWidth().padding(vertical = 10.dp)
                        .padding(horizontal = 20.dp)
                        .padding(vertical = 5.dp)
                ) {

                    Text("Select the Distance", fontFamily = Poppins, fontSize = 15.sp, fontWeight = FontWeight.Medium)
                    ExposedDropdownMenuBox(
                        expanded = radiusExpanded,
                        onExpandedChange = { radiusExpanded = it },

                        ) {
                        TextField(
                            value = radiusSelected,
                            onValueChange = { radiusSelected = it },
                            readOnly = true,
                            modifier = Modifier
                                .clip(RoundedCornerShape(12.dp))
                                .border(0.5.dp, Color.Black, RoundedCornerShape(12.dp))
                                .menuAnchor()
                                .width(120.dp)
                                .height(50.dp),
                            textStyle = TextStyle(fontSize = 12.sp, fontFamily = Poppins),
                            trailingIcon = {
                                ExposedDropdownMenuDefaults.TrailingIcon(expanded = radiusExpanded)
                            },
                            colors = TextFieldDefaults.colors(
                                unfocusedIndicatorColor = Color.Transparent,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedContainerColor = Color.Transparent,
                                focusedContainerColor = Color.Transparent

                            )
                        )

                        ExposedDropdownMenu(expanded = radiusExpanded,
                            onDismissRequest = { radiusExpanded = false }) {
                            radiusOptions.forEach { options ->
                                DropdownMenuItem(text = { Text(options, fontFamily = Poppins) },
                                    onClick = {
                                        radiusSelected = options
                                        radiusExpanded = false
                                    },
                                    modifier = Modifier.height(36.dp)
                                )
                            }
                        }
                    }
                }




                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = modifier
                        .fillMaxWidth().padding(vertical = 10.dp)
                        .padding(horizontal = 20.dp)
                        .padding(vertical = 5.dp)
                ) {

                    Text("Type of Hospital", fontFamily = Poppins, fontSize = 15.sp, fontWeight = FontWeight.Medium)
                    ExposedDropdownMenuBox(
                        expanded = hospitalExpanded,
                        onExpandedChange = { hospitalExpanded = it },

                        ) {
                        TextField(
                            value = hospitalSelected,
                            onValueChange = { hospitalSelected = it },
                            readOnly = true,
                            modifier = Modifier
                                .clip(RoundedCornerShape(12.dp))
                                .border(0.5.dp, Color.Black, RoundedCornerShape(12.dp))
                                .menuAnchor()
                                .width(120.dp)
                                .height(50.dp),
                            textStyle = TextStyle(fontSize = 12.sp, fontFamily = Poppins),
                            trailingIcon = {
                                ExposedDropdownMenuDefaults.TrailingIcon(expanded = hospitalExpanded)
                            },
                            colors = TextFieldDefaults.colors(
                                unfocusedIndicatorColor = Color.Transparent,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedContainerColor = Color.Transparent,
                                focusedContainerColor = Color.Transparent
                            )
                        )

                        ExposedDropdownMenu(expanded = hospitalExpanded,
                            onDismissRequest = { hospitalExpanded = false }) {
                            hospitalOptions.forEach { options ->
                                DropdownMenuItem(text = { Text(options, fontFamily = Poppins) },
                                    onClick = {
                                        hospitalSelected = options
                                        hospitalExpanded = false
                                    },
                                    modifier = Modifier.height(36.dp)
                                )
                            }
                        }
                    }
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 40.dp),
                contentAlignment = Alignment.Center
            ) {
                LottieAnimation(
                    composition = loading, modifier = Modifier.size(300.dp), // Base animation
<<<<<<< HEAD
                    iterations = if (animation) LottieConstants.IterateForever else 1
=======
                    iterations = 2
>>>>>>> 4e42eac (Added ICP and Frontend)
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 60.dp),
                contentAlignment = Alignment.BottomCenter
            ) {
                Button(
                    onClick = {
                        showPermissionDialog = true
<<<<<<< HEAD
                        animation = true
=======
>>>>>>> 4e42eac (Added ICP and Frontend)
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .height(70.dp),
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(Color(0xFF02c29b)),
                    elevation = ButtonDefaults.elevatedButtonElevation(12.dp)
                ) {
                    Text("Search Nearby Hospitals", fontFamily = Poppins, fontSize = 18.sp, color = Color.Black, fontWeight = FontWeight.SemiBold)
                }

                if (showPermissionDialog) {
                    LocationPermissions(onPermissionGranted = {
<<<<<<< HEAD
                        searchViewModel.onPermissionGranted(radius = finalRadius)
                    }, onPermissionDenied = {
                        Toast.makeText(context, "Location permission is required to find nearby hospitals", Toast.LENGTH_LONG).show()
                    }, resetTrigger = {showPermissionDialog = false})
                    showPermissionDialog = false // Dismiss after checking permissions
                }

                LaunchedEffect(hospitalsList) {
                    if (hospitalsList.isNotEmpty()) {
                        animation = false
                        navHostController.navigate("searchResults") // Replace with your actual navigation route
                    }
                }
=======
                        searchViewModel.onPermissionGranted()
                    })
                    showPermissionDialog = false // Dismiss after checking permissions
                }
>>>>>>> 4e42eac (Added ICP and Frontend)
            }

        }
    }
}