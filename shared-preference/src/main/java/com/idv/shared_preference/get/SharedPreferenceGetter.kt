package com.idv.shared_preference.get

import android.content.Context
import java.lang.ref.WeakReference

interface SharedPreferenceGetter {

    fun getToken() : String?

    class Builder() {

        private lateinit var contextRef: WeakReference<Context>

        fun setContext(context: Context) = apply {
            this.contextRef = WeakReference(context)
        }

        fun build() : SharedPreferenceGetter? {
            contextRef.get()?.let { context ->
                return SharedPreferenceGetterImpl(context)
            }
            return null
        }
    }
}