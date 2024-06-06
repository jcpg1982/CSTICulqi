package com.csticorp.culqi.test.presentation.composables

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun SingleLineTextField(
    modifier: Modifier = Modifier,
    text: String,
    onValueChange: (String) -> Unit,
    labelText: String? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
) {
    BaseTextField(
        modifier = modifier,
        text = text,
        onValueChange = onValueChange,
        labelText = labelText,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        isSingleLine = true,
        trailingIcon = trailingIcon
    )
}