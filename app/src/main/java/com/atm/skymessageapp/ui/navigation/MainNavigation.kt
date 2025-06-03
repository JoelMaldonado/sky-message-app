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
import com.atm.skymessageapp.ui.features.SendMessages.SendMessagesScreen


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
                    navController.navigate(Routes.Home)
                }
            )
        }

        composable<Routes.Home> {
            HomeScreen(
                toSendMessages = {
                    navController.navigate(Routes.SendMessagesScreen(14))
                }
            )
        }

        composable<Routes.SendMessagesScreen> {
            val args = it.toRoute<Routes.SendMessagesScreen>()
            SendMessagesScreen(
                userId = args.userId,
            )
        }
    }

}