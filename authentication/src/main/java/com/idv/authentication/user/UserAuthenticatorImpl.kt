package com.idv.authentication.user

import com.idv.authentication.user.service.AuthenticatorService
import java.io.IOException

internal class UserAuthenticatorImpl(private val service: AuthenticatorService) : UserAuthenticator {
    override suspend fun checkAuth(token: String): Boolean = service.checkAuth(token)

    @Throws(IOException::class)
    override suspend fun auth(email: String, password: String): Boolean = service.auth(email, password)
}