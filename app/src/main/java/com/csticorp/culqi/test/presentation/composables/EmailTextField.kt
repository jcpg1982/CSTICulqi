package com.csticorp.culqi.test.presentation.composables

import android.util.Patterns
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import com.csticorp.culqi.test.R
import com.csticorp.culqi.test.presentation.composables.state.ComponentState


@Composable
fun EmailTextField(
    modifier: Modifier = Modifier,
    text: String,
    onValueChange: (String) -> Unit,
    onState: ComponentState? = null,
    onStateChange: ((ComponentState) -> Unit)? = null,
) {
    BaseTextField(
        modifier = modifier,
        text = text,
        onValueChange = {
            val emailFormatResult = Patterns.EMAIL_ADDRESS.matcher(it).matches()
            val emailNotEmptyResult = it.isNotEmpty()

            if (emailFormatResult && emailNotEmptyResult) {
                onStateChange?.invoke(ComponentState.Valid)
            } else {
                if (!emailFormatResult) onStateChange?.invoke(ComponentState.Invalid("Ingrese un email correcto por favor"))
                if (!emailNotEmptyResult) onStateChange?.invoke(ComponentState.Invalid("El campo no debe estar vacío"))
            }
            onValueChange(it)
        },
        labelText = stringResource(id = R.string.email),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        isError = if (onState is ComponentState.Invalid) {
            !onState.message.isNullOrEmpty()
        } else false,
        errorText = if (onState is ComponentState.Invalid) onState.message else null,
    )
}