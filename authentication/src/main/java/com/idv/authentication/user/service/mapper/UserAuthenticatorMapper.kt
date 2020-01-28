package com.idv.authentication.user.service.mapper

import com.idv.authentication.user.get.UserAuthenticator
import com.idv.authentication.user.service.AccountType
import com.idv.authentication.user.service.RetrofitModel.AuthenticationResponseModel

internal object UserAuthenticatorMapper {
    fun map(response: AuthenticationResponseModel): UserAuthenticator {
        return UserAuthenticator(response.message?.toLowerCase() == "ok", mapAccountType(response.user?.userType?: ""))
    }

    private fun mapAccountType(userType: String): AccountType {
        if(userType.toLowerCase() == "admin")
            return AccountType.ADMIN

        return AccountType.SELLER
    }
}