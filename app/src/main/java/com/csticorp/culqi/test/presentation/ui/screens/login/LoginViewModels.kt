package com.csticorp.culqi.test.presentation.ui.screens.login

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.csticorp.culqi.test.data.request.LoginRequest
import com.csticorp.culqi.test.data.response.DataUser
import com.csticorp.culqi.test.data.response.LoginUserResponse
import com.csticorp.culqi.test.domain.usesCase.LoginUserUseCase
import com.csticorp.culqi.test.domain.utils.Constants.IntentsExtras.DATA_USER
import com.csticorp.culqi.test.domain.utils.Utils.stringToObject
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
class LoginViewModels @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val loginUserUseCase: LoginUserUseCase
) : ViewModel() {

    private var _loginUser: MutableStateFlow<LoginScreenState> =
        MutableStateFlow(LoginScreenState.Loading(false, ""))
    val loginUser get() = _loginUser.asStateFlow()

    private var _password: MutableStateFlow<String> = MutableStateFlow("")
    val password get() = _password.asStateFlow()
    private var _dataUser: MutableStateFlow<DataUser?> = MutableStateFlow(null)
    val dataUser get() = _dataUser.asStateFlow()
    private var _isEnabledButton: MutableStateFlow<Boolean> = MutableStateFlow(enableButton)
    val isEnabledButton get() = _isEnabledButton.asStateFlow()

    private val isDataCorrect: Boolean
        get() = dataUser.value?.password.orEmpty() == password.value

    init {
        savedStateHandle.get<String>(DATA_USER)?.let { bundle ->
            bundle.stringToObject(DataUser::class.java).let { dataUser ->
                _dataUser.update { dataUser }
            }
        }
    }

    private val enableButton: Boolean
        get() = password.value.isNotEmpty()

    fun setPassword(password: String) {
        _password.update { password }
        _isEnabledButton.update { enableButton }
    }

    fun loginUser() {
        _loginUser.update { LoginScreenState.Loading(true, "") }
        if (isDataCorrect) {
            viewModelScope.launch {
                loginUserUseCase.invoke(LoginRequest())
                    .flowOn(Dispatchers.IO)
                    .onStart { _loginUser.update { LoginScreenState.Loading(true, "") } }
                    .catch { e ->
                        _loginUser.update { LoginScreenState.Error(e) }
                    }.collect { result ->
                        _loginUser.update {
                            if (result.isSuccessful) {
                                LoginScreenState.Success(result.body() ?: LoginUserResponse(""))
                            } else {
                                LoginScreenState.Error(Throwable(result.message()))
                            }
                        }
                    }
            }
        } else {
            _loginUser.update { LoginScreenState.Error(Throwable("Verificar su contraseña")) }
        }
    }
}