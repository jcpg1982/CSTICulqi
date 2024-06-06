package com.csticorp.culqi.test.presentation.ui.screens.login

import com.csticorp.culqi.test.data.response.LoginUserResponse

sealed interface LoginScreenState {

    data class Loading(val isLoading: Boolean, val message: String) : LoginScreenState
    data class Error(val error: Throwable) : LoginScreenState
    data class Success(val data: LoginUserResponse) : LoginScreenState
}