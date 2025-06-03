package com.atm.skymessageapp.data.network.request

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class LoginRequest(
    @SerializedName("user") val username: String,
    @SerializedName("password") val password: String
)