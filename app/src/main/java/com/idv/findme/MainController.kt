package com.idv.findme

import androidx.lifecycle.ViewModel
import com.idv.authentication.user.get.UserAuthenticatorGetter
import com.idv.core.service.RetrofitServiceFactory

class MainController() : ViewModel() {

    class Builder() {
        fun build(): MainController {
            val userAuthenticator = UserAuthenticatorGetter.Builder().setRetrofitFactory(RetrofitServiceFactory).build()
            return MainController()
        }
    }
}