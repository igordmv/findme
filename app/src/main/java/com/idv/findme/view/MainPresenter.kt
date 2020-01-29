package com.idv.findme.view

import androidx.lifecycle.MutableLiveData
import com.idv.authentication.user.get.UserAuthenticator
import com.idv.findme.splash.view.UserAuthenticatorViewModel
import com.idv.findme.view.mapper.MainMapper

internal interface MainPresenter {

    fun presentLoginState(authenticated: UserAuthenticator)

    fun presentError()

    fun presentLoadingState(showLoading : Boolean)

    fun getAuthenticationObservable(): MutableLiveData<UserAuthenticatorViewModel>

    fun getErrorObservable(): MutableLiveData<Boolean>

    fun getLoadingObservable(): MutableLiveData<Boolean>

    companion object Factory {
        fun make() : MainPresenter{
            val mapper = MainMapper
            return MainPresenterImpl(mapper)
        }
    }
}