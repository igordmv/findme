package com.idv.findme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.idv.authentication.user.UserAuthenticator
import com.idv.core.service.RetrofitServiceFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import java.io.IOException

class MainController(private val userAuthenticator: UserAuthenticator) : ViewModel() {

    fun checkAuth(email: String, password: String) = viewModelScope.async(Dispatchers.IO) {
        try {
            val teste = userAuthenticator.auth(email, password)
        } catch (e: IOException) {

        }

    }

    class Builder() {
        fun build(): MainController {
            val userAuthenticator = UserAuthenticator.Builder().setRetrofitFactory(RetrofitServiceFactory).build()
            return MainController(userAuthenticator)
        }
    }
}