package com.idv.authentication.user.service.RetrofitModel

import com.google.gson.annotations.SerializedName
import com.idv.authentication.user.service.UserModel

internal class LoginResponseModel {

    @SerializedName("token")
    var token: String? = null

    @SerializedName("user")
    var user: UserModel? = null
}