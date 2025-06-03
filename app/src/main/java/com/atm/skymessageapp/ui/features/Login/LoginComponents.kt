package com.atm.skymessageapp.ui.features.Login

data class LoginComponents(
    val error: String? = null,
    val isLoading: Boolean = false,
    val errorUser: String? = null,
    val errorPassword: String? = null,

    val isVisiblePassword: Boolean = false,

    val isSuccessLogin: Boolean = false,

) {
    fun isValid(): Boolean {
        return errorUser == null && errorPassword == null
    }
}
