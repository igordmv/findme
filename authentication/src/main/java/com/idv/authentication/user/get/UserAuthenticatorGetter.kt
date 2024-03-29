package com.idv.authentication.user.get

import com.idv.authentication.user.service.AuthenticatorServiceImpl
import com.idv.authentication.user.service.mapper.UserAuthenticatorMapper
import com.idv.core.service.ServiceFactory
import java.io.IOException

interface UserAuthenticatorGetter {

    @Throws(IOException::class)
    suspend fun auth(username: String, password: String) : UserAuthenticator

    @Throws(IOException::class)
    suspend fun checkToken(token: String) : UserAuthenticator

    class Builder(){

        private lateinit var factory: ServiceFactory

        fun setRetrofitFactory(factory: ServiceFactory) = apply {
            this.factory = factory
        }

        fun build() : UserAuthenticatorGetter {
            val mapper = UserAuthenticatorMapper
            val service = AuthenticatorServiceImpl(factory)
            return UserAuthenticatorGetterImpl(service, mapper)
        }
    }

}