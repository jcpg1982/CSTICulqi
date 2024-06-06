package com.csticorp.culqi.test.presentation.ui.screens.init

import com.csticorp.culqi.test.data.response.DataUser

sealed interface InitScreenState {

    data class Loading(val isLoading: Boolean, val message: String) : InitScreenState
    data class Error(val error: Throwable) : InitScreenState
    data class Success(val dataList: List<DataUser>) : InitScreenState
}