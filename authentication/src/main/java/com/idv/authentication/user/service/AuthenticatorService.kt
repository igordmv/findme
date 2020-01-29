package com.idv.authentication.user.service

import com.idv.authentication.user.service.RetrofitModel.AuthenticationResponseModel
import com.idv.authentication.user.service.RetrofitModel.LoginResponseModel
import java.io.IOException

internal interface AuthenticatorService {
    @Throws(IOException::class)
    suspend fun auth(email: String, password: String): LoginResponseModel

    @Throws(IOException::class)
    suspend fun checkToken(token: String) : AuthenticationResponseModel
}
