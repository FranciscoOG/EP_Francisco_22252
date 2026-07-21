package com.example.ep_francisco_22252

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ep_francisco_22252.screens.*
import com.example.ep_francisco_22252.data.appdb.AppDB
import com.example.ep_francisco_22252.network.RetrofitClient
import com.example.ep_francisco_22252.ui.theme.EP_Francisco_22252Theme
import kotlinx.coroutines.launch
import com.example.ep_francisco_22252.data.*
import com.example.ep_francisco_22252.viewmodel.*

class MainActivity : ComponentActivity() {
    private lateinit var db: AppDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        db = AppDB.getdb(this)
        lifecycleScope.launch {
            try {
                /*val getair = RetrofitClient.service.getAir(lat,long)
                if (getair.isNotEmpty()) {
                    db.airdao().clearAir()
                    db.airdao().saveAir(getair)
                }*/
            } catch (e: Exception) {
                //showMessage("Sem conexao")
            }
        }
        setContent {
            val navController = rememberNavController()
            EP_Francisco_22252Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val firstViewModel = airViewModel(db)

                    NavHost(navController = navController, startDestination = FirstScreenRoute) {
                        composable<FirstScreenRoute> {
                            FirstScreen(navController = navController, viewModel = firstViewModel)
                        }
                    }
                }
            }
        }
    }
}