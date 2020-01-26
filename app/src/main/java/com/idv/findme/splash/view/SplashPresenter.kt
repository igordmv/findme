package com.idv.findme.splash.view

import androidx.lifecycle.MutableLiveData

internal interface SplashPresenter {

    fun getAuthenticationObservable(): MutableLiveData<Boolean>

    fun presentAuthenticationState(authenticated: Boolean)

    fun getErrorObservable(): MutableLiveData<Boolean>

    fun presentErrorState(hasError: Boolean)
}