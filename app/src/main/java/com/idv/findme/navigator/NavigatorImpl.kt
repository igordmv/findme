package com.idv.findme.navigator

import android.content.Context
import android.content.Intent
import com.idv.admin.view.AdminActivity
import com.idv.findme.view.MainActivity
import com.idv.seller.view.SellerActivity
import java.lang.ref.WeakReference

internal class NavigatorImpl(context: Context) : Navigator {

    val contextRef = WeakReference(context)

    override fun navigateLoginScreen() {
        contextRef.get()?.let { context ->
            val mainIntent = Intent(context, MainActivity::class.java)
            context.startActivity(mainIntent)
        }

    }

    override fun navigateSellerScreen() {
        contextRef.get()?.let { context ->
            val mainIntent = Intent(context, SellerActivity::class.java)
            context.startActivity(mainIntent)
        }
    }

    override fun navigateAdminScreen() {
        contextRef.get()?.let { context ->
            val mainIntent = Intent(context, AdminActivity::class.java)
            context.startActivity(mainIntent)
        }
    }
}