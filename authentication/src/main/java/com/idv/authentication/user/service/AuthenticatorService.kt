package com.idv.authentication.user.service

import java.io.IOException

internal interface AuthenticatorService {
    @Throws(IOException::class)
    suspend fun auth(email: String, password: String): Boolean

    @Throws(IOException::class)
    suspend fun checkAuth(token: String) : Boolean
}
