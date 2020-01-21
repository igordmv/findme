package com.idv.authentication.user.service

import com.idv.core.service.ServiceFactory
import java.io.IOException

class AuthenticatorServiceImpl(factory: ServiceFactory) : AuthenticatorService {
    private val service: AuthenticatorRetrofitService = factory.make(BASE_URL, AuthenticatorRetrofitService::class.java)
    override suspend fun auth(email: String, password: String): Boolean {
        try {
            val res1 = service.getAll().execute()

            val response = service.auth(email, password).execute()
            if (response.isSuccessful)
                response.body()?.let {
                    return@let true
                }

            val e = IOException("${response.code()}")
            e.printStackTrace()
            throw e

        } catch (e: IOException) {
            e.printStackTrace()
            throw e
        }
    }

    companion object {
        private const val BASE_URL = "http://177.17.38.249:3000/"
    }
}