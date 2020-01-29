package com.idv.shared_preference.set

import android.content.Context
import com.idv.shared_preference.get.SharedPreferenceGetterImpl
import java.lang.ref.WeakReference
import com.idv.shared_preference.get.SharedPreferenceGetterImpl.Companion.AUTHENTICATION_TOKEN

internal class SharedPreferenceSetterImpl(context: Context) : SharedPreferenceSetter {

    private val weakContext = WeakReference(context)

    override fun setToken(token: String) {
        weakContext.get()?.let { context ->
            val sharedPreference= context.getSharedPreferences(SharedPreferenceGetterImpl.SHARED_PREFERENCE_KEY, Context.MODE_PRIVATE)
            val edit = sharedPreference.edit()
            edit.putString(AUTHENTICATION_TOKEN, token)
            edit.commit()
        }
    }
}