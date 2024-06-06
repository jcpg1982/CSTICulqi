package com.csticorp.culqi.test.presentation.composables

data class DialogCallback(
    val onPrimaryButtonClick: (Boolean) -> Unit,
    val onSecondaryButtonClick: (Boolean) -> Unit,
    val onTextClickableClick: (Boolean) -> Unit,
    val onDismmissClick: (Boolean) -> Unit,
)
