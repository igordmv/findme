package com.idv.authentication.user.get

import com.idv.authentication.user.service.AuthenticatorService
import com.idv.authentication.user.service.mapper.UserAuthenticatorMapper
import java.io.IOException

internal class UserAuthenticatorGetterImpl(private val service: AuthenticatorService,
                                           private val mapper : UserAuthenticatorMapper) :
    UserAuthenticatorGetter {
    override suspend fun checkToken(token: String): UserAuthenticator = mapper.map(service.checkToken(token))

    @Throws(IOException::class)
    override suspend fun auth(email: String, password: String): Boolean = service.auth(email, password)
}