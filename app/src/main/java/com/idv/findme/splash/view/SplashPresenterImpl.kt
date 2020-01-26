package com.idv.findme.splash.view

import androidx.lifecycle.MutableLiveData

internal class SplashPresenterImpl : SplashPresenter {
    private val authenticationObserver = MutableLiveData<Boolean>()
    private val errorObserver = MutableLiveData<Boolean>()

    override fun presentAuthenticationState(authenticated: Boolean) = authenticationObserver.postValue(authenticated)

    override fun presentErrorState(hasError: Boolean) = errorObserver.postValue(hasError)

    override fun getAuthenticationObservable() = authenticationObserver

    override fun getErrorObservable() = errorObserver
}