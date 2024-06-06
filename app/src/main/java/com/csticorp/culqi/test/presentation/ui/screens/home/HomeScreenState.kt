package com.csticorp.culqi.test.presentation.ui.screens.home

import com.csticorp.culqi.test.data.response.DataUser

sealed interface HomeScreenState {

    data object First : HomeScreenState
    data object FirstLoading : HomeScreenState
    data object MoreLoading : HomeScreenState
    data class Error(val error: Throwable) : HomeScreenState
    data class Success(val dataList: List<DataUser>) : HomeScreenState
}