package com.idv.authentication.user.service

import android.util.Log
import com.idv.authentication.user.service.RetrofitModel.AuthRequest
import com.idv.core.service.ServiceFactory
import java.io.IOException

class AuthenticatorServiceImpl(factory: ServiceFactory) : AuthenticatorService {
    private val service: AuthenticatorRetrofitService = factory.make(BASE_URL, AuthenticatorRetrofitService::class.java)

    override suspend fun checkToken(token: String): Boolean {
        try {
            val response = service.checkToken(token).execute()
            Log.i("IGOR", response.message())
            return(response.body()?.message?.toLowerCase() == "ok")
        } catch (e : IOException){
            e.printStackTrace()
            throw e
        }
    }

    override suspend fun auth(email: String, password: String): Boolean {
        try {

            val response = service.auth(AuthRequest(email, password)).execute()
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
        private const val BASE_URL = "http://3.14.29.42:3000/"
    }
}