package com.atm.skymessageapp.data.network.api

import com.atm.skymessageapp.data.network.request.LoginRequest
import com.atm.skymessageapp.data.network.responses.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("auth/login")
    suspend fun login(
        @Body() request: LoginRequest
    ): Response<LoginResponse>

}