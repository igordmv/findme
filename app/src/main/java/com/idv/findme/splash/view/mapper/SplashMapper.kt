package com.idv.findme.splash.view.mapper

import com.idv.authentication.user.get.UserAuthenticator
import com.idv.authentication.user.service.AccountType
import com.idv.findme.splash.view.AccountTypeViewModel
import com.idv.findme.splash.view.UserAuthenticatorViewModel

internal object SplashMapper {
    fun map(userAuthenticator: UserAuthenticator): UserAuthenticatorViewModel {
        return UserAuthenticatorViewModel(
            userAuthenticator.authenticated,
            mapAccountType(userAuthenticator.accountType)
        )
    }

    private fun mapAccountType(accountType: AccountType): AccountTypeViewModel {
        return when (accountType) {
            AccountType.SELLER -> AccountTypeViewModel.SELLER
            AccountType.ADMIN -> AccountTypeViewModel.ADMIN
        }
    }
}