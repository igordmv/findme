package com.idv.shared_preference.set

import android.content.Context
import java.lang.ref.WeakReference

interface SharedPreferenceSetter {

    fun setToken(token : String)

    class Builder() {

        private lateinit var contextRef: WeakReference<Context>

        fun setContext(context: Context) = apply {
            this.contextRef = WeakReference(context)
        }

        fun build() : SharedPreferenceSetter? {
            contextRef.get()?.let { context ->
                return SharedPreferenceSetterImpl(context)
            }
            return null
        }
    }
}