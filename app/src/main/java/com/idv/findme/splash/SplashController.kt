package com.idv.findme.splash

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.idv.authentication.user.get.UserAuthenticatorGetter
import com.idv.core.service.RetrofitServiceFactory
import com.idv.findme.navigator.Navigator
import com.idv.findme.splash.view.SplashActivity
import com.idv.findme.splash.view.SplashPresenter
import com.idv.findme.splash.view.SplashPresenterImpl
import com.idv.findme.splash.view.UserAuthenticatorViewModel
import com.idv.findme.splash.view.mapper.SplashMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException
import java.lang.ref.WeakReference

internal class SplashController(
    private val userAuthenticator: UserAuthenticatorGetter,
    private val presenter: SplashPresenter,
    private val navigator: Navigator
) : ViewModel() {

    fun checkAuth(token: String) = viewModelScope.launch(Dispatchers.IO) {
        try {
            val authenticated = userAuthenticator.checkToken(token)
            presenter.presentAuthenticationState(authenticated)
        } catch (e: IOException) {
            presenter.presentErrorState(true)
        }
    }

    fun sellerAuthenticatedUserLoggedIn() = viewModelScope.launch(Dispatchers.IO){
        navigator.navigateSellerScreen()
    }

    fun adminAuthenticatedUserLoggedIn() = viewModelScope.launch(Dispatchers.IO){
        navigator.navigateAdminScreen()
    }

    fun userNotAuthenticated() = viewModelScope.launch(Dispatchers.IO){
        navigator.navigateLoginScreen()
    }

    class Builder {
        private lateinit var activityRef: WeakReference<SplashActivity>
        private lateinit var authenticationObserver: Observer<UserAuthenticatorViewModel>
        private lateinit var errorObserver: Observer<Boolean>

        fun setActivity(activity: SplashActivity) = apply {
            this.activityRef = WeakReference(activity)
        }

        fun setAuthenticationObserver(authenticationObserver: Observer<UserAuthenticatorViewModel>) = apply {
            this.authenticationObserver = authenticationObserver
        }

        fun setErrorObserver(errorObserver: Observer<Boolean>) = apply {
            this.errorObserver = errorObserver
        }

        fun build(): SplashController? {
            val mapper = SplashMapper
            val presenter = SplashPresenter.Builder().setMapper(mapper).build()
            var activity = activityRef.get()
            activity?.let { activity ->
                val navigator = Navigator.Builder().setContext(activity).build()
                presenter.getAuthenticationObservable().observe(activity, authenticationObserver)
                presenter.getErrorObservable().observe(activity, errorObserver)
                val userAuthenticator = UserAuthenticatorGetter.Builder().setRetrofitFactory(RetrofitServiceFactory).build()
                return SplashController(userAuthenticator, presenter, navigator)
            }
            return null
        }
    }
}