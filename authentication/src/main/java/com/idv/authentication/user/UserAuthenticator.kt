package com.idv.authentication.user

import com.idv.authentication.user.service.AuthenticatorServiceImpl
import com.idv.core.service.ServiceFactory
import java.io.IOException

interface UserAuthenticator {

    @Throws(IOException::class)
    suspend fun auth(email: String, password: String) : Boolean

    @Throws(IOException::class)
    suspend fun checkAuth(token: String) : Boolean

    class Builder(){

        private lateinit var factory: ServiceFactory

        fun setRetrofitFactory(factory: ServiceFactory) = apply {
            this.factory = factory
        }

        fun build() : UserAuthenticator {
            val service = AuthenticatorServiceImpl(factory)
            return UserAuthenticatorImpl(service)
        }
    }

}