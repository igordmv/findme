package com.idv.findme

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.idv.authentication.user.get.UserAuthenticatorGetter
import com.idv.core.service.RetrofitServiceFactory
import com.idv.findme.navigator.Navigator
import com.idv.findme.splash.view.SplashActivity
import com.idv.findme.splash.view.UserAuthenticatorViewModel
import com.idv.findme.view.MainActivity
import com.idv.findme.view.MainPresenter
import com.idv.shared_preference.set.SharedPreferenceSetter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException
import java.lang.ref.WeakReference

internal class MainController(
    private val userAuthenticator: UserAuthenticatorGetter,
    private val presenter: MainPresenter,
    private val sharedPreferenceSetter : SharedPreferenceSetter?,
    private val navigator: Navigator
) : ViewModel() {

    fun loginButtonClicked(username: String, password: String) =
        viewModelScope.launch(Dispatchers.IO) {
            try {
                presenter.presentLoadingState(true)
                val authenticated = userAuthenticator.auth(username, password)
                presenter.presentLoginState(authenticated)
            } catch (e: IOException) {
                presenter.presentError()
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

    fun setToken(token : String) {
        sharedPreferenceSetter?.let {
            it.setToken(token)
        }
    }

    class Builder() {
        private lateinit var activityRef: WeakReference<MainActivity>
        private lateinit var loadingObserver: Observer<Boolean>
        private lateinit var errorObserver: Observer<Boolean>
        private lateinit var authenticationObserver: Observer<UserAuthenticatorViewModel>

        fun setActivity(activity: MainActivity) = apply {
            this.activityRef = WeakReference(activity)
        }

        fun setAuthenticationObserver(authenticationObserver: Observer<UserAuthenticatorViewModel>) = apply {
            this.authenticationObserver = authenticationObserver
        }

        fun setErrorObserver(errorObserver: Observer<Boolean>) = apply {
            this.errorObserver = errorObserver
        }

        fun setLoadingObserver(loadingObserver: Observer<Boolean>) = apply {
            this.loadingObserver = loadingObserver
        }

        fun build(): MainController? {

            var activity = activityRef.get()
            activity?.let { activity ->
                val presenter = MainPresenter.make()
                presenter.getAuthenticationObservable().observe(activity, authenticationObserver)
                presenter.getErrorObservable().observe(activity, errorObserver)
                presenter.getLoadingObservable().observe(activity, loadingObserver)

                val navigator = Navigator.Builder().setContext(activity).build()

                val userAuthenticator =
                    UserAuthenticatorGetter.Builder().setRetrofitFactory(RetrofitServiceFactory).build()

                val sharedPreferenceSetter = SharedPreferenceSetter.Builder().setContext(activity).build()

                return MainController(userAuthenticator, presenter, sharedPreferenceSetter, navigator)
            }
            return null
        }
    }
}