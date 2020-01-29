package com.idv.findme.navigator

import android.content.Context

internal interface Navigator {
    fun navigateLoginScreen()

    fun navigateSellerScreen()

    fun navigateAdminScreen()

    class Builder() {

        private lateinit var context: Context

        fun setContext(context : Context) = apply {
            this.context = context
        }

        fun build() : Navigator {
            return NavigatorImpl(context)
        }

    }
}