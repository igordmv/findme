package com.idv.authentication.user

import com.idv.authentication.user.service.AuthenticatorService
import java.io.IOException

internal class UserAuthenticatorImpl(private val service: AuthenticatorService) : UserAuthenticator {

    @Throws(IOException::class)
    override suspend fun auth(email: String, password: String): Boolean = service.auth(email, password)
}