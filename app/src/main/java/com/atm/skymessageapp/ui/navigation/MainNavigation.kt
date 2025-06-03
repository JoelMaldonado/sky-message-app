package com.atm.skymessageapp.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.atm.skymessageapp.ui.features.Home.HomeScreen
import com.atm.skymessageapp.ui.features.Login.LoginScreen


@Composable
fun MainNavigation(
    paddingValues: PaddingValues,
) {

    val navController = rememberNavController()

    NavHost(
        modifier = Modifier.padding(paddingValues),
        navController = navController,
        startDestination = Routes.Login
    ) {

        composable<Routes.Login> {
            LoginScreen(
                toHome = {
                    navController.navigate(Routes.Home(24))
                }
            )
        }

        composable<Routes.Home> {
            val args = it.toRoute<Routes.Home>()
            HomeScreen(
                userId = args.userId
            )
        }

    }

}