package com.idv.authentication.user.service

import com.google.gson.annotations.SerializedName

class LocationModel (
    @SerializedName("type")
    var type : String? = null,

    @SerializedName("_id")
    var id : String? = null,

    @SerializedName("coordinates")
    var coordinates : String? = null
    )