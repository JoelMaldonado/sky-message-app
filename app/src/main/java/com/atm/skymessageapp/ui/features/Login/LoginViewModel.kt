package com.atm.skymessageapp.ui.features.Login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.atm.skymessageapp.core.Result
import com.atm.skymessageapp.core.validators.PasswordValidator
import com.atm.skymessageapp.core.validators.UserValidator
import com.atm.skymessageapp.domain.usecase.auth.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    var state by mutableStateOf(LoginState())
        private set

    var components by mutableStateOf(LoginComponents())
        private set

    fun onEvent(e: LoginEvent) {
        when (e) {
            is LoginEvent.SetUser -> handleSetUser(e.value)
            is LoginEvent.SetPassword -> handleSetPassword(e.value)
            LoginEvent.Login -> login()
            LoginEvent.TogglePasswordVisibility -> {
                components = components.copy(isVisiblePassword = !components.isVisiblePassword)
            }

            LoginEvent.ClearSuccessLogin -> components = components.copy(isSuccessLogin = false)
            LoginEvent.ClearError -> components = components.copy(error = null)
        }
    }

    private fun login() {
        viewModelScope.launch {
            components = components.copy(
                isLoading = true,
                errorUser = UserValidator.validate(state.user),
                errorPassword = PasswordValidator.validate(state.password)
            )

            if (components.isValid() == false) {
                return@launch
            }

            val res = withContext(Dispatchers.IO) {
                loginUseCase(
                    username = state.user,
                    password = state.password
                )
            }

            components = when (res) {
                is Result.Error -> components.copy(
                    isLoading = false,
                    error = res.exception.message
                )

                is Result.Success -> components.copy(
                    isLoading = false,
                    isSuccessLogin = true
                )
            }
        }
    }

    private fun handleSetUser(value: String) {
        state = state.copy(user = value)
        components = components.copy(errorUser = UserValidator.validate(value))
    }

    private fun handleSetPassword(value: String) {
        state = state.copy(password = value)
        components = components.copy(errorPassword = PasswordValidator.validate(value))
    }
}