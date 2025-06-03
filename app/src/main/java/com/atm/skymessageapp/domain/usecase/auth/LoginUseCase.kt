package com.atm.skymessageapp.domain.usecase.auth

import com.atm.skymessageapp.core.Result
import com.atm.skymessageapp.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(
        username: String,
        password: String
    ): Result<String> {
        return repository.login(
            username = username,
            password = password
        )
    }
}