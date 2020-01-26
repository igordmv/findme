package com.idv.findme.splash

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.idv.authentication.user.UserAuthenticator
import com.idv.core.service.RetrofitServiceFactory
import com.idv.findme.splash.view.SplashActivity
import com.idv.findme.splash.view.SplashPresenter
import com.idv.findme.splash.view.SplashPresenterImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException
import java.lang.ref.WeakReference

internal class SplashController(
    private val userAuthenticator: UserAuthenticator,
    private val presenter: SplashPresenter
) : ViewModel() {

    fun checkAuth(token: String) = viewModelScope.launch(Dispatchers.IO) {
        try {
            val authenticated = userAuthenticator.checkAuth(token)
            presenter.presentAuthenticationState(authenticated)
        } catch (e: IOException) {
            presenter.presentErrorState(true)
        }
    }

    class Builder {
        private lateinit var activityRef: WeakReference<SplashActivity>
        private lateinit var authenticationObserver: Observer<Boolean>
        private lateinit var errorObserver: Observer<Boolean>

        fun setActivity(activity: SplashActivity) = apply {
            this.activityRef = WeakReference(activity)
        }

        fun setAuthenticationObserver(authenticationObserver: Observer<Boolean>) = apply {
            this.authenticationObserver = authenticationObserver
        }

        fun setErrorObserver(errorObserver: Observer<Boolean>) = apply {
            this.errorObserver = errorObserver
        }

        fun build(): SplashController? {
            val presenter = SplashPresenterImpl()
            var activity = activityRef.get()
            activity?.let { activity ->
                presenter.getAuthenticationObservable().observe(activity, authenticationObserver)
                presenter.getErrorObservable().observe(activity, errorObserver)
                val userAuthenticator = UserAuthenticator.Builder().setRetrofitFactory(RetrofitServiceFactory).build()
                return SplashController(userAuthenticator, presenter)
            }
            return null
        }
    }
}