package com.csticorp.culqi.test.presentation.composables.state

sealed class ComponentState {
    data class Invalid(val message: String? = null) : ComponentState()
    data object Valid : ComponentState()
}