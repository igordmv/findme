package com.idv.findme.splash.view

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.idv.core.extensions.runOnBackground
import com.idv.findme.R
import com.idv.findme.splash.SplashController


class SplashActivity : AppCompatActivity() {
    private var controller: SplashController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        this.controller = SplashController
            .Builder()
            .setActivity(this)
            .setAuthenticationObserver(authenticationObserver)
            .setErrorObserver(errorObserver)
            .build()

        var token = controller?.getToken()

        Handler().postDelayed({
            token?.let { token ->
                runOnBackground {
                    controller?.checkAuth(token)
                }
            } ?: run {
                controller?.userNotAuthenticated()
            }
        }, 3 * SECONDS)
    }

    private val authenticationObserver =
        Observer<UserAuthenticatorViewModel> { userAuthenticatorViewModel ->
            if (userAuthenticatorViewModel.authenticated) {
                when (userAuthenticatorViewModel.userType) {
                    AccountTypeViewModel.SELLER -> controller?.sellerAuthenticatedUserLoggedIn()
                    AccountTypeViewModel.ADMIN ->  controller?.adminAuthenticatedUserLoggedIn()
                }
            } else {
                controller?.userNotAuthenticated()
            }
        }

    private val errorObserver = Observer<Boolean> { hasError ->
        if (hasError) {

        } else {

        }
    }

    companion object {
        const val AUTHENTICATION_TOKEN: String = "AUTHENTICATION_TOKEN"
        const val SECONDS: Long = 1000
    }
}
