package com.atm.skymessageapp.data.repository

import com.atm.skymessageapp.data.network.api.ApiService
import com.atm.skymessageapp.data.network.request.LoginRequest
import com.atm.skymessageapp.domain.repository.AuthRepository
import javax.inject.Inject
import com.atm.skymessageapp.core.Result
import com.atm.skymessageapp.data.database.dao.MessageDao

class AuthRepositoryImpl @Inject constructor(
    private val api: ApiService,
    private val dao: MessageDao
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
                Result.Success("Login successful")
            } else {
                Result.Error(Exception("Login failed with code: ${call.code()}"))
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}