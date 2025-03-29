package com.bytebandits.sanjeevani.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bytebandits.sanjeevani.ui.theme.Poppins
import com.bytebandits.sanjeevani.viewmodels.SearchViewModel

@Composable
fun SearchResults(searchViewModel: SearchViewModel, modifier: Modifier = Modifier) {


    val hospitalsList by searchViewModel.hospitalList.observeAsState()
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
            LazyColumn {
                hospitalsList?.let {
                    items(it.size) { index ->
                        val hosp = hospitalsList!![index]
                        ResultsCard(hosp.name, hosp.distance)
                    }
                }

            }
            println(hospitalsList?.size)

        }
    }
}


@Composable
fun ResultsCard(name: String?, distance: String?, modifier: Modifier = Modifier) {
    Box(modifier.padding(vertical = 10.dp)) {
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier
                .padding(vertical = 30.dp)
                .padding(horizontal = 20.dp)) {
                Text(name?: "Invalid Name", fontFamily = Poppins, fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
                Text(
                    "Distance - ${distance?.toDoubleOrNull()?.let { String.format("%.2f km", it) } ?: "Invalid Distance"}",
                    fontFamily = Poppins,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}