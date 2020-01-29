package com.idv.authentication.user.service

import com.globo.globosatplay.avc.check.service.ResponseModel
import com.idv.authentication.user.service.RetrofitModel.AuthRequest
import com.idv.authentication.user.service.RetrofitModel.AuthenticationResponseModel
import com.idv.authentication.user.service.RetrofitModel.LoginResponseModel
import com.idv.authentication.user.service.RetrofitModel.NameModel
import retrofit2.Call
import retrofit2.http.*

internal interface AuthenticatorRetrofitService {

    @POST("/user/auth")
    fun auth(@Body authRequest: AuthRequest): Call<LoginResponseModel>

    @GET("/user/get")
    fun getAll() : Call<List<NameModel>>

    @GET("/user/checkToken")
    fun checkToken(@Header("Authorization") token: String) : Call<AuthenticationResponseModel>
}