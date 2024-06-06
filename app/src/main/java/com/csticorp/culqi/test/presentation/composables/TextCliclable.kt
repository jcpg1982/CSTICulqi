package com.csticorp.culqi.test.presentation.composables

import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import com.csticorp.culqi.test.presentation.ui.theme.DynamicEighteenTextSize
import com.csticorp.culqi.test.presentation.ui.theme.PoppinsRegular

@Composable
fun TextClickable(
    modifier: Modifier = Modifier,
    fullText: String,
    clickableText: String,
    normalTextColor: Color = Color.White,
    clickableTextColor: Color = Color.Blue,
    onClick: () -> Unit
) {
    val textBefore = fullText.substringBefore(clickableText)
    val textAfter = fullText.substringAfter(clickableText)

    val annotatedString = buildAnnotatedString {
        withStyle(style = SpanStyle(color = normalTextColor)) {
            append(textBefore)
        }
        withStyle(
            style = SpanStyle(
                color = clickableTextColor,
                textDecoration = TextDecoration.Underline
            )
        ) {
            append(clickableText)
        }
        withStyle(style = SpanStyle(color = normalTextColor)) {
            append(textAfter)
        }
    }

    ClickableText(
        modifier = modifier, text = annotatedString, onClick = { offset ->
            if (offset in textBefore.length until textBefore.length + clickableText.length) {
                onClick()
            }
        },
        style = TextStyle(
            fontSize = DynamicEighteenTextSize,
            fontFamily = PoppinsRegular
        )
    )
}