package com.idv.authentication.user.service

import android.util.Log
import com.idv.authentication.user.service.RetrofitModel.AuthRequest
import com.idv.authentication.user.service.RetrofitModel.AuthenticationResponseModel
import com.idv.authentication.user.service.RetrofitModel.LoginResponseModel
import com.idv.core.service.ServiceFactory
import java.io.IOException

internal class AuthenticatorServiceImpl(factory: ServiceFactory) : AuthenticatorService {
    private val service: AuthenticatorRetrofitService =
        factory.make(BASE_URL, AuthenticatorRetrofitService::class.java)

    override suspend fun checkToken(token: String): AuthenticationResponseModel {
        try {
            val response = service.checkToken(token).execute()
            return response.body()!!
        } catch (e: Exception) {
            throw IOException(e.message)
        } catch (e: IOException) {
            e.printStackTrace()
            throw e
        }
    }

    override suspend fun auth(email: String, password: String): LoginResponseModel {
        try {

            val response = service.auth(AuthRequest(email, password)).execute()
            if(response.isSuccessful)
                return response.body()!!

            val e = IOException("${response.code()}")
            e.printStackTrace()
            throw e

        } catch (e: Exception) {
            e.printStackTrace()
            throw IOException(e.message)
        } catch (e: IOException) {
            e.printStackTrace()
            throw e
        }
    }

    companion object {
        private const val BASE_URL = "http://3.14.29.42:3000/"
    }
}