package com.idv.findme.view

import androidx.lifecycle.MutableLiveData
import com.idv.authentication.user.get.UserAuthenticator
import com.idv.findme.splash.view.UserAuthenticatorViewModel
import com.idv.findme.view.mapper.MainMapper

internal class MainPresenterImpl(private val mapper: MainMapper) : MainPresenter {
    private val loadingObservable = MutableLiveData<Boolean>()
    private val errorObserver = MutableLiveData<Boolean>()
    private val authenticationObserver = MutableLiveData<UserAuthenticatorViewModel>()

    override fun presentLoginState(authenticated: UserAuthenticator) {
        authenticationObserver.postValue(mapper.map(authenticated))
        loadingObservable.postValue(false)
    }

    override fun presentLoadingState(showLoading : Boolean) = loadingObservable.postValue(showLoading)

    override fun presentError() {
        errorObserver.postValue(true)
        loadingObservable.postValue(false)
    }

    override fun getAuthenticationObservable() = authenticationObserver

    override fun getErrorObservable() = errorObserver

    override fun getLoadingObservable(): MutableLiveData<Boolean> = loadingObservable
}