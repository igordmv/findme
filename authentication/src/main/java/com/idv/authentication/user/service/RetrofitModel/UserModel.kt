package com.idv.authentication.user.service

import com.google.gson.annotations.SerializedName

internal class UserModel(
    @SerializedName("user_type")
    var userType : String? = null,

    @SerializedName("_id")
    var id : String? = null,

    @SerializedName("name")
    var name : String? = null,

    @SerializedName("email")
    var email : String? = null,

    @SerializedName("age")
    var age : Int? = null,

    @SerializedName("bairro")
    var bairro : String? = null,

    @SerializedName("createdAt")
    var createdAt : String? = null,

    @SerializedName("updatedAt")
    var updatedAt : String? = null,

    @SerializedName("location")
    var location : LocationModel? = null
    )