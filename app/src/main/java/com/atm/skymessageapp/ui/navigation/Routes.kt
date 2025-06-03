package com.atm.skymessageapp.ui.navigation

import kotlinx.serialization.Serializable

object Routes {

    @Serializable
    object Login

    @Serializable
    data class Home(
        val userId: Int
    )
}
