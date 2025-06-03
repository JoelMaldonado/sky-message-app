package com.atm.skymessageapp.domain.repository

import com.atm.skymessageapp.core.Result

interface AuthRepository {
    suspend fun login(username: String, password: String): Result<String>
}