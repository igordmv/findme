package com.idv.findme.splash.view

import androidx.lifecycle.MutableLiveData
import com.idv.authentication.user.get.UserAuthenticator
import com.idv.findme.splash.view.mapper.SplashMapper

internal interface SplashPresenter {

    fun getAuthenticationObservable(): MutableLiveData<UserAuthenticatorViewModel>

    fun presentAuthenticationState(authenticated: UserAuthenticator)

    fun getErrorObservable(): MutableLiveData<Boolean>

    fun presentErrorState(hasError: Boolean)

    class Builder {
        private lateinit var mapper: SplashMapper

        fun setMapper(mapper: SplashMapper) = apply {
            this.mapper = mapper
        }

        fun build(): SplashPresenter {
            return SplashPresenterImpl(mapper)
        }

    }
}