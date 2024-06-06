package com.csticorp.culqi.test.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.csticorp.culqi.test.data.model.Routes
import com.csticorp.culqi.test.domain.utils.Constants.IntentsExtras.DATA_USER
import com.csticorp.culqi.test.domain.utils.Constants.IntentsExtras.EMAIL
import com.csticorp.culqi.test.domain.utils.Utils.getEnterTransition
import com.csticorp.culqi.test.domain.utils.Utils.getExitTransition
import com.csticorp.culqi.test.presentation.ui.screens.home.HomeScreen
import com.csticorp.culqi.test.presentation.ui.screens.init.InitScreen
import com.csticorp.culqi.test.presentation.ui.screens.login.LoginScreen
import com.csticorp.culqi.test.presentation.ui.screens.signup.SignUpScreen
import com.csticorp.culqi.test.presentation.viewModels.NavigationViewModel

@Composable
fun NavigationMain(
    navController: NavHostController,
    startDestination: String,
    navigationViewModel: NavigationViewModel
) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(route = Routes.Init.route) { InitScreen(navigationViewModel) }
        composable(route = Routes.SignUpScreen().route,
            arguments = listOf(navArgument(EMAIL) { type = NavType.StringType }),
            enterTransition = { getEnterTransition },
            exitTransition = { getExitTransition },
            popEnterTransition = { getEnterTransition },
            popExitTransition = { getExitTransition }) { SignUpScreen(navigationViewModel) }
        composable(route = Routes.LoginScreen().route,
            arguments = listOf(navArgument(DATA_USER) { type = NavType.StringType }),
            enterTransition = { getEnterTransition },
            exitTransition = { getExitTransition },
            popEnterTransition = { getEnterTransition },
            popExitTransition = { getExitTransition }) { LoginScreen(navigationViewModel) }
        composable(route = Routes.Home.route,
            enterTransition = { getEnterTransition },
            exitTransition = { getExitTransition },
            popEnterTransition = { getEnterTransition },
            popExitTransition = { getExitTransition }) { HomeScreen(navigationViewModel) }
    }
}