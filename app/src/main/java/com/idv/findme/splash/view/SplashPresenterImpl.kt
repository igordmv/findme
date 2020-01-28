package com.idv.findme.splash.view

import androidx.lifecycle.MutableLiveData
import com.idv.authentication.user.get.UserAuthenticator
import com.idv.findme.splash.view.mapper.SplashMapper

internal class SplashPresenterImpl(private val mapper : SplashMapper) : SplashPresenter {
    private val authenticationObserver = MutableLiveData<UserAuthenticatorViewModel>()
    private val errorObserver = MutableLiveData<Boolean>()

    override fun presentAuthenticationState(authenticated: UserAuthenticator) {
        val viewModel = mapper.map(authenticated)
        authenticationObserver.postValue(viewModel)
    }

    override fun presentErrorState(hasError: Boolean) = errorObserver.postValue(hasError)

    override fun getAuthenticationObservable() = authenticationObserver

    override fun getErrorObservable() = errorObserver
}