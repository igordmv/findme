package com.idv.authentication.user.service

import com.globo.globosatplay.avc.check.service.ResponseModel
import com.idv.authentication.user.service.RetrofitModel.NameModel
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.POST

internal interface AuthenticatorRetrofitService {

    @POST("/user/auth")
    fun auth(@Field("email") email: String?,
             @Field("password") password: String?): Call<ResponseModel>

    @GET("/user/get")
    fun getAll() : Call<List<NameModel>>
}