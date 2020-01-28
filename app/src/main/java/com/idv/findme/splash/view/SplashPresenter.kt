package com.idv.findme.splash.view

import androidx.lifecycle.MutableLiveData
import com.idv.authentication.user.get.UserAuthenticator

internal interface SplashPresenter {

    fun getAuthenticationObservable(): MutableLiveData<UserAuthenticatorViewModel>

    fun presentAuthenticationState(authenticated: UserAuthenticator)

    fun getErrorObservable(): MutableLiveData<Boolean>

    fun presentErrorState(hasError: Boolean)
}