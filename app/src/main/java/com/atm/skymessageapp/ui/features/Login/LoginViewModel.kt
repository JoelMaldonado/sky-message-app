package com.atm.skymessageapp.ui.features.Login

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.atm.skymessageapp.core.validators.PasswordValidator
import com.atm.skymessageapp.core.validators.UserValidator
import com.atm.skymessageapp.domain.usecase.auth.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
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
        }
    }

    private fun login() {
        viewModelScope.launch {
            components = components.copy(isLoading = true)
            delay(2000)
            components = components.copy(
                errorUser = UserValidator.validate(state.user),
                errorPassword = PasswordValidator.validate(state.password)
            )
            if (components.isValid() == false) {
                components = components.copy(isLoading = false)
                Log.d("tagito", "No paso ${components.isValid()}")
                return@launch
            }
            Log.d("tagito", "Si paso")

            val res = withContext(Dispatchers.IO) {
                loginUseCase.invoke(
                    username = state.user,
                    password = state.password
                )
            }

            res.fold(
                onFailure = {
                    Log.d("tagito", it.message ?: "Unknown error")
                },
                onSuccess = {
                    Log.d("tagito", "Login successful: $it")
                }
            )

            components = components.copy(isLoading = false)
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