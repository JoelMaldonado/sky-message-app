package com.atm.skymessageapp.ui.features.Login

sealed class LoginEvent {
    data class SetUser(val value: String) : LoginEvent()
    data class SetPassword(val value: String) : LoginEvent()
    object Login: LoginEvent()
    object TogglePasswordVisibility : LoginEvent()
}