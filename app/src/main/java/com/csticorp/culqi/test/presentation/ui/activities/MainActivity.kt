package com.csticorp.culqi.test.presentation.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.csticorp.culqi.test.presentation.ui.navigation.NavigationMain
import com.csticorp.culqi.test.presentation.ui.theme.TestTheme
import com.csticorp.culqi.test.presentation.viewModels.NavigationViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val navigationViewModel: NavigationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val navController = rememberNavController()
            val currentRoute by navigationViewModel.currentRoute.collectAsState()

            TestTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        NavigationMain(
                            navController = navController,
                            startDestination = currentRoute.route,
                            navigationViewModel = navigationViewModel
                        )
                    }
                }
            }

            LaunchedEffect(currentRoute) {
                navController.navigate(currentRoute.route) {
                    launchSingleTop = true
                    restoreState = true
                }
            }
        }
    }
}