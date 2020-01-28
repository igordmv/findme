package com.idv.authentication.user.get

import com.idv.authentication.user.service.AccountType

data class UserAuthenticator (
    val authenticated : Boolean,
    var accountType : AccountType
)
