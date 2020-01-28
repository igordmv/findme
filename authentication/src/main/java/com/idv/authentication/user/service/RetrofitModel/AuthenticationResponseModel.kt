package com.idv.authentication.user.service.RetrofitModel

import com.google.gson.annotations.SerializedName
import com.idv.authentication.user.service.UserModel

internal class AuthenticationResponseModel {

    @SerializedName("messageUserModel")
    var message: String? = null

    @SerializedName("user")
    var user: UserModel? = null
}
