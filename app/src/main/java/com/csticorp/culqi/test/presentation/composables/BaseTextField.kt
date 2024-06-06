package com.csticorp.culqi.test.presentation.composables

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.csticorp.culqi.test.presentation.ui.theme.BlueGray300
import com.csticorp.culqi.test.presentation.ui.theme.BlueGray500
import com.csticorp.culqi.test.presentation.ui.theme.BlueGray900
import com.csticorp.culqi.test.presentation.ui.theme.Orange500
import com.csticorp.culqi.test.presentation.ui.theme.Red500
import com.csticorp.culqi.test.presentation.ui.theme.Turquoise500
import com.csticorp.culqi.test.presentation.ui.theme.textStyle14

@Composable
internal fun BaseTextField(
    modifier: Modifier = Modifier,
    text: String,
    placeHolder: String? = null,
    placeHolderColor: Color = BlueGray500,
    onValueChange: (String) -> Unit,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    labelText: String? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardActions: KeyboardActions = KeyboardActions(),
    keyboardOptions: KeyboardOptions = KeyboardOptions(),
    isError: Boolean = false,
    errorText: String? = null,
    isEnabled: Boolean = true,
    isSingleLine: Boolean = true,
    hasBorder: Boolean = true,
    textStyle: TextStyle = textStyle14
) {

    var hasFocus by remember { mutableStateOf(false) }

    val borderColor = if (isError) Orange500 else {
        if (hasFocus) Turquoise500 else Red500
    }

    val labelColor = if (isError) Orange500 else BlueGray300

    val decorationBoxModifier = if (hasBorder) {
        Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = borderColor,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(horizontal = 16.dp)
    } else {
        Modifier.fillMaxWidth()
    }

    val placeHolderModifier = if (hasBorder) Modifier
        .padding(start = 16.dp)
    else Modifier

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Box(contentAlignment = Alignment.Center) {
            BasicTextField(
                modifier = Modifier
                    .onFocusChanged {
                        hasFocus = it.isFocused
                    }
                    .height(50.dp),
                value = text,
                onValueChange = onValueChange,
                textStyle = textStyle,
                visualTransformation = visualTransformation,
                keyboardActions = keyboardActions,
                keyboardOptions = keyboardOptions,
                decorationBox = { innerTextField ->
                    Row(
                        modifier = decorationBoxModifier,
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        leadingIcon?.invoke()

                        Column(
                            modifier = Modifier
                                .weight(1f)
                        ) {
                            labelText?.let {
                                Text12(text = labelText, color = labelColor)
                            }
                            innerTextField()
                        }

                        trailingIcon?.invoke()
                    }
                    placeHolder?.let {
                        if (text.isEmpty()) {
                            Box(contentAlignment = Alignment.Center) {
                                if (hasBorder) {
                                    Text14(
                                        modifier = placeHolderModifier,
                                        text = it,
                                        color = placeHolderColor,
                                        textAlign = TextAlign.Start,
                                    )
                                } else {
                                    Text24(
                                        modifier = placeHolderModifier,
                                        text = it,
                                        color = placeHolderColor,
                                        textAlign = TextAlign.Start,
                                    )
                                }
                            }
                        }
                    }
                },
                cursorBrush = SolidColor(BlueGray900),
                singleLine = isSingleLine,
                enabled = isEnabled,
            )
        }

        if (isError) {
            errorText?.let {
                Spacer(modifier = Modifier.size(4.dp))
                Text12(text = errorText, color = Red500)
            }
        }
    }
}