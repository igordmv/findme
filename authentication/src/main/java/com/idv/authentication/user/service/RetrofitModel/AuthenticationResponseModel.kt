package com.idv.authentication.user.service.RetrofitModel

import com.google.gson.annotations.SerializedName

internal class AuthenticationResponseModel {

    @SerializedName("msg")
    var message: String? = null
}