package com.csticorp.culqi.test.presentation.ui.screens.signup

import com.csticorp.culqi.test.data.response.RegisterUserResponse

sealed interface SignUpScreenState {

    data class Loading(val isLoading: Boolean, val message: String) : SignUpScreenState
    data class Error(val error: Throwable) : SignUpScreenState
    data class Success(val data: RegisterUserResponse) : SignUpScreenState
}