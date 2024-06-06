package com.csticorp.culqi.test.presentation.ui.screens.signup

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.csticorp.culqi.test.data.request.RegisterUserRequest
import com.csticorp.culqi.test.data.response.DataUser
import com.csticorp.culqi.test.data.response.RegisterUserResponse
import com.csticorp.culqi.test.domain.usesCase.RegisterUserUseCase
import com.csticorp.culqi.test.domain.utils.Constants.IntentsExtras.EMAIL
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
class SignUpViewModels @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val registerUserUseCase: RegisterUserUseCase
) : ViewModel() {

    private var _registerUser: MutableStateFlow<SignUpScreenState> =
        MutableStateFlow(SignUpScreenState.Loading(false, ""))
    val registerUser get() = _registerUser.asStateFlow()

    private var _email: MutableStateFlow<String> = MutableStateFlow("")
    val email get() = _email.asStateFlow()
    private var _fullName: MutableStateFlow<String> = MutableStateFlow("")
    val fullName get() = _fullName.asStateFlow()
    private var _password: MutableStateFlow<String> = MutableStateFlow("")
    val password get() = _password.asStateFlow()
    private var _dataUser: MutableStateFlow<DataUser> = MutableStateFlow(DataUser())
    val dataUser get() = _dataUser.asStateFlow()
    private var _isEnabledButton: MutableStateFlow<Boolean> = MutableStateFlow(enableButton)
    val isEnabledButton get() = _isEnabledButton.asStateFlow()

    init {
        savedStateHandle.get<String>(EMAIL)?.let { setEmail(it) }
    }

    private val enableButton: Boolean
        get() {
            var isValid = false
            if (fullName.value.isNotEmpty()) {
                isValid = password.value.isNotEmpty()
            }
            return isValid
        }

    private fun setEmail(email: String) {
        _email.update { email }
        _isEnabledButton.update { enableButton }
        _dataUser.update { it.copy(email = email) }
    }

    fun setFullName(fullName: String) {
        _fullName.update { fullName }
        _isEnabledButton.update { enableButton }
        val splitName = fullName.split(" ")
        if (splitName.size >= 2)
            _dataUser.update { it.copy(firstName = splitName[0], lastName = splitName[1]) }
        else _dataUser.update { it.copy(firstName = splitName[0], lastName = "") }

    }

    fun setPassword(password: String) {
        _password.update { password }
        _isEnabledButton.update { enableButton }
        _dataUser.update { it.copy(password = password) }
    }

    fun registerUser() {
        viewModelScope.launch {
            registerUserUseCase.invoke(RegisterUserRequest())
                .flowOn(Dispatchers.IO)
                .onStart { _registerUser.update { SignUpScreenState.Loading(true, "") } }
                .catch { e ->
                    _registerUser.update { SignUpScreenState.Error(e) }
                }.collect { result ->
                    _registerUser.update {
                        if (result.isSuccessful) {
                            SignUpScreenState.Success(result.body() ?: RegisterUserResponse(-1, ""))
                        } else {
                            SignUpScreenState.Error(Exception(result.message()))
                        }
                    }
                }
        }
    }
}