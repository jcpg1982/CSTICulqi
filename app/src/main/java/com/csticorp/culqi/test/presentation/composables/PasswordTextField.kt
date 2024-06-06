package com.csticorp.culqi.test.presentation.composables

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.csticorp.culqi.test.R
import com.csticorp.culqi.test.presentation.composables.state.ComponentState

@Composable
fun PasswordTextField(
    modifier: Modifier = Modifier,
    text: String,
    onValueChange: (String) -> Unit,
    isPasswordVisible: Boolean,
    onPasswordVisibleValueChange: (Boolean) -> Unit,
    labelText: String? = null,
    placeHolder: String? = null,
    onState: ComponentState? = null,
    onStateChange: ((ComponentState) -> Unit)? = null,
) {

    val maxLength = 12
    val mingLength = 6

    val image = if (isPasswordVisible) painterResource(id = R.drawable.ic_eye_on) else painterResource(id = R.drawable.ic_eye_off)

    val description = if (isPasswordVisible) stringResource(id = R.string.hide_password) else stringResource(id = R.string.show_password)

    BaseTextField(
        modifier = modifier,
        text = text,
        onValueChange = {
            onStateChange?.invoke(
                if (it.isEmpty()) ComponentState.Invalid("Completar el campo")
                else ComponentState.Valid
            )
            onValueChange(it)
        },
        trailingIcon = {
            IconButton(onClick = { onPasswordVisibleValueChange(!isPasswordVisible) }) {
                Icon(painter = image, contentDescription = description, tint = Color.Unspecified)
            }
        },
        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        labelText = labelText,
        isError = if (onState is ComponentState.Invalid) {
            !onState.message.isNullOrEmpty()
        } else false,
        errorText = if (onState is ComponentState.Invalid) onState.message else null,
        placeHolder = placeHolder
    )
}