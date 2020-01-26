package com.idv.findme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.idv.authentication.user.UserAuthenticator
import com.idv.core.service.RetrofitServiceFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import java.io.IOException

class MainController() : ViewModel() {

    class Builder() {
        fun build(): MainController {
            val userAuthenticator = UserAuthenticator.Builder().setRetrofitFactory(RetrofitServiceFactory).build()
            return MainController()
        }
    }
}