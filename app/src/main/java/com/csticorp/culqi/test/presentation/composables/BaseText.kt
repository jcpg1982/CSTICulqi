package com.csticorp.culqi.test.presentation.composables

import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import com.csticorp.culqi.test.presentation.ui.theme.PoppinsRegular

@Composable
fun BaseText(
    modifier: Modifier,
    text: String,
    textAlign: TextAlign? = null,
    fontFamily: FontFamily = PoppinsRegular,
    fontWeight: FontWeight? = null,
    color: Color = Color.Unspecified,
    lineHeight: TextUnit = TextUnit.Unspecified,
    fontSize: TextUnit = TextUnit.Unspecified,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    softWrap: Boolean = true,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    style: TextStyle = LocalTextStyle.current
) {
    Text(
        modifier = modifier,
        text = text,
        textAlign = textAlign,
        fontFamily = fontFamily,
        fontWeight = fontWeight,
        color = color,
        lineHeight = lineHeight,
        fontSize = fontSize,
        maxLines = maxLines,
        minLines = minLines,
        softWrap = softWrap,
        onTextLayout = onTextLayout,
        style = style,
    )
}

@Composable
fun BaseText(
    modifier: Modifier,
    text: AnnotatedString,
    textAlign: TextAlign? = null,
    fontFamily: FontFamily = PoppinsRegular,
    fontWeight: FontWeight? = null,
    color: Color = Color.Unspecified,
    lineHeight: TextUnit = TextUnit.Unspecified,
    fontSize: TextUnit = TextUnit.Unspecified,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    softWrap: Boolean = true,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    style: TextStyle = LocalTextStyle.current
) {
    Text(
        modifier = modifier,
        text = text,
        textAlign = textAlign,
        fontFamily = fontFamily,
        fontWeight = fontWeight,
        color = color,
        lineHeight = lineHeight,
        fontSize = fontSize,
        maxLines = maxLines,
        minLines = minLines,
        softWrap = softWrap,
        onTextLayout = onTextLayout,
        style = style,
    )
}