package com.idv.shared_preference.get

import android.content.Context
import java.lang.ref.WeakReference

internal class SharedPreferenceGetterImpl(context : Context) : SharedPreferenceGetter {

    private val weakContext = WeakReference(context)

    override fun getToken(): String? {
        weakContext.get()?.let { context ->
            val sharedPreference= context.getSharedPreferences(SHARED_PREFERENCE_KEY, Context.MODE_PRIVATE)
            return sharedPreference.getString(AUTHENTICATION_TOKEN, null)
        }
        return null
    }

    companion object {
        const val SHARED_PREFERENCE_KEY: String = "SHARED_PREFERENCE_KEY"
        const val AUTHENTICATION_TOKEN: String = "AUTHENTICATION_TOKEN"
    }
}