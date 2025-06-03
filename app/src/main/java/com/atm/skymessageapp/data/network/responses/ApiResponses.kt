package com.atm.skymessageapp.data.network.responses

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class LoginResponse(
    @SerializedName("access_token") val accessToken: String,
    @SerializedName("token_type") val tokenType: String,
)