package com.csticorp.culqi.test.presentation.ui.screens.init

import android.util.Patterns
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class InitViewModels @Inject constructor() : ViewModel() {

    private var _getAllUsers: MutableStateFlow<InitScreenState> =
        MutableStateFlow(InitScreenState.Loading(false, ""))
    val getAllUsers get() = _getAllUsers.asStateFlow()

    private var _email: MutableStateFlow<String> = MutableStateFlow("")
    val email get() = _email.asStateFlow()
    private var _isEnabledButton: MutableStateFlow<Boolean> = MutableStateFlow(enableButton)
    val isEnabledButton get() = _isEnabledButton.asStateFlow()

    private val enableButton: Boolean
        get() {
            var isValid = false
            if (email.value.isNotEmpty()) {
                isValid = Patterns.EMAIL_ADDRESS.matcher(email.value).matches()
            }
            return isValid
        }

    fun setEmail(email: String) {
        _email.update { email }
        _isEnabledButton.update { enableButton }
    }
}