package com.atm.skymessageapp.ui.navigation

import kotlinx.serialization.Serializable

object Routes {

    @Serializable
    object Login

    @Serializable
    object Home

    @Serializable
    data class SendMessagesScreen(
        val userId: Int
    )
}
