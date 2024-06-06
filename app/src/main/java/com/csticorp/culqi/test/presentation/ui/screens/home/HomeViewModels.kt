package com.csticorp.culqi.test.presentation.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.csticorp.culqi.test.domain.usesCase.AllUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModels @Inject constructor(
    private val allUsersUseCase: AllUsersUseCase
) : ViewModel() {

    private var _getAllUsers: MutableStateFlow<HomeScreenState> =
        MutableStateFlow(HomeScreenState.First)
    val getAllUsers get() = _getAllUsers.asStateFlow()
    var isLastPage: Boolean = false

    var page = 1
    var perPage = 5

    init {
        getAllUsers()
        page = 1
    }

    fun getAllUsers() {
        if (!isLastPage) {
            viewModelScope.launch {
                allUsersUseCase.invoke(page = page, perPage = perPage).flowOn(Dispatchers.IO)
                    .onStart {
                        _getAllUsers.update {
                            if (page == 1) HomeScreenState.FirstLoading
                            else HomeScreenState.MoreLoading
                        }
                    }.catch { e ->
                        _getAllUsers.update { HomeScreenState.Error(e) }
                    }.collect { result ->
                        _getAllUsers.update {
                            if (result.isSuccessful) {
                                HomeScreenState.Success(result.body()?.result ?: emptyList())
                            } else {
                                HomeScreenState.Error(Throwable(result.message()))
                            }
                        }
                        isLastPage = (result.body()?.result?.size ?: 0) < perPage
                    }
            }
        }
    }
}