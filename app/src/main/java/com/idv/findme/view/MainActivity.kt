package com.idv.findme.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import com.idv.core.extensions.runOnUI
import com.idv.findme.MainController
import com.idv.findme.R
import com.idv.findme.splash.view.AccountTypeViewModel
import com.idv.findme.splash.view.UserAuthenticatorViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var controller: MainController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loginCardView.setOnClickListener(loginOnClickListener)
        controller = MainController
            .Builder()
            .setErrorObserver(errorObserver)
            .setAuthenticationObserver(authenticationObserver)
            .setLoadingObserver(loadingObserver)
            .setActivity(this)
            .build()
    }

    private val errorObserver = Observer<Boolean> { hasError ->
        if (hasError) {
            Toast.makeText(this, TOAST_FAIL_LOGIN, Toast.LENGTH_LONG).show()
        } else {

        }
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

    private val loadingObserver = Observer<Boolean> { showLoading ->
        if(showLoading)
            progressbar.visibility = View.VISIBLE
        else
            progressbar.visibility = View.GONE
    }

    private val loginOnClickListener = View.OnClickListener { view ->
        runOnUI {
            if (view == loginCardView) {
                val username = usernameEditText.text.toString()
                val password = passwordEditText.text.toString()
                if (username != "" && password != "") {
                    controller?.loginButtonClicked(username, password)
                } else {
                    Toast.makeText(this, TOAST_EMPTY_LOGIN_TEXT, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    companion object {
        const val TOAST_EMPTY_LOGIN_TEXT = "Por favor preencha todos os campos."
        const val TOAST_FAIL_LOGIN = "Login e/ou senha incorretos."
    }
}
