package com.idv.findme.splash.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.idv.core.extensions.runOnBackground
import com.idv.findme.R
import com.idv.findme.splash.SplashController
import com.idv.findme.view.MainActivity
import com.idv.seller.view.SellerActivity

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

        val sharedPreference = getSharedPreferences(SHARED_PREFERENCE_KEY, Context.MODE_PRIVATE)
        var token = sharedPreference.getString(AUTHENTICATION_TOKEN, null)
        token?.let { token ->
            runOnBackground {
                controller?.checkAuth(token)
            }
        } ?: run {
            navigateLoginScreen()
        }
    }

    private fun navigateLoginScreen() {
        val mainIntent = Intent(this@SplashActivity, MainActivity::class.java)
        startActivity(mainIntent)
        finish()
    }

    private fun navigateSellerScreen() {
        val mainIntent = Intent(this@SplashActivity, SellerActivity::class.java)
        startActivity(mainIntent)
        finish()
    }

    private val authenticationObserver = Observer<UserAuthenticatorViewModel> { userAuthenticatorViewModel ->
        if(userAuthenticatorViewModel.authenticated) {
            when(userAuthenticatorViewModel.userType){
                AccountTypeViewModel.SELLER ->  navigateSellerScreen()
                AccountTypeViewModel.ADMIN -> {

                }
            }
        } else {
            navigateLoginScreen()
        }
    }

    private val errorObserver = Observer<Boolean> { hasError ->
        if(hasError) {

        } else {

        }
    }

    companion object {
        const val SHARED_PREFERENCE_KEY: String = "SHARED_PREFERENCE_KEY"
        const val AUTHENTICATION_TOKEN: String = "AUTHENTICATION_TOKEN"
    }
}
