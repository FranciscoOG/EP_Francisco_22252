package com.example.ep_francisco_22252.screens

import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.items
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
import com.example.ep_francisco_22252.SecondScreenRoute
import com.example.ep_francisco_22252.data.Air
import com.example.ep_francisco_22252.viewmodel.airViewModel
import com.example.ep_francisco_22252.data.City
import com.example.ep_francisco_22252.data.cities

@Composable
fun FirstScreen(navController: NavController, viewModel: airViewModel) {
    val state by viewModel.uiState.collectAsState()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = "UN ODS List", style = MaterialTheme.typography.headlineSmall)

        if (state.Loading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        } else if (state.errorMessage != null) {
            Text(text = state.errorMessage!!, color = MaterialTheme.colorScheme.error)
        } else {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(cities) { city ->
                    AirCard(city = city, navController = navController)
                }
            }
        }
    }
}


@Composable
fun AirCard(city: City, navController: NavController) {
    val line1 = "City: " +  city.name

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
                .clickable {
                    navController.navigate(
                        SecondScreenRoute(
                            city.latitude, city.longitude
                        )
                    )
                },
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