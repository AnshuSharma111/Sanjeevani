package com.bytebandits.sanjeevani.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bytebandits.sanjeevani.ui.theme.Poppins

@Composable
fun HospitalSpecificDetailsScreen(modifier: Modifier = Modifier) {

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Column(modifier = Modifier.offset(y = (40).dp)) {

            Text(
                "Sanjeevani",
                fontFamily = Poppins,
                fontWeight = FontWeight.SemiBold,
                fontSize = 24.sp,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            Card(modifier = Modifier.fillMaxWidth()) {
                Column(Modifier.padding(20.dp).padding(vertical = 10.dp)) {
                    Text("LifeCare Hospital", fontFamily = Poppins, fontSize = 16.sp, fontWeight = FontWeight.SemiBold , modifier = modifier.padding(bottom = 8.dp))
                    Text("Mohali, Chandigarh, India", fontFamily = Poppins, fontSize = 14.sp, fontWeight = FontWeight.Medium)
                }
            }
        }
    }
}

