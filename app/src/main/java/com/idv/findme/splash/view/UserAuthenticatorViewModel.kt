package com.idv.findme.splash.view

internal data class UserAuthenticatorViewModel (
    val authenticated : Boolean,
    val userType : AccountTypeViewModel
)