package com.globo.globosatplay.avc.check.service

import com.google.gson.annotations.SerializedName
import com.idv.authentication.user.service.UserModel

internal class ResponseModel(

    @SerializedName("token")
    var token : String? = null,

    @SerializedName("user")
    var user: UserModel? = null)

