package com.example.ep_francisco_22252.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ep_francisco_22252.data.Air
import com.example.ep_francisco_22252.viewmodel.airViewModel
import com.example.ep_francisco_22252.data.appdb.AppDB
import kotlinx.coroutines.flow.Flow

@Composable
fun SecondScreen(lat: Double, long: Double, navController: NavController, viewModel: airViewModel, db: AppDB) {
    val state by viewModel.uiState.collectAsState()
    val cityAir = db.airdao().getAirQuality(lat,long)

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = "UN ODS List", style = MaterialTheme.typography.headlineSmall)

        if (state.Loading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        } else if (state.errorMessage != null) {
            Text(text = state.errorMessage!!, color = MaterialTheme.colorScheme.error)
        } else {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                item() {
                    OdsCard(city, navController = navController)
                }
            }
        }
    }
}

@Composable
fun OdsCard(air: Air, navController: NavController) {
    val line1 = "PM10 = " + cityAir.pm10.toString() + "\nPM2.5 = " + cityAir.pm2_5.toString()

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Left Indicator Line
            Box(
                modifier = Modifier
                    .width(6.dp)
                    .fillMaxHeight()
            )

            // Card content
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp)
            ) {
                Text(
                    text = line1,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}