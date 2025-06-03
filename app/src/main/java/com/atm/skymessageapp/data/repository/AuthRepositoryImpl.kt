package com.atm.skymessageapp.data.repository

import com.atm.skymessageapp.data.network.api.ApiService
import com.atm.skymessageapp.data.network.request.LoginRequest
import com.atm.skymessageapp.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val api: ApiService
) : AuthRepository {

    override suspend fun login(
        username: String,
        password: String
    ): Result<String> {
        return try {
            val request = LoginRequest(
                username = username,
                password = password
            )
            val call = api.login(request)
            if (call.isSuccessful) {
                Result.success("Login successful")
            } else {
                Result.failure(Exception("Login failed with code: ${call.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}