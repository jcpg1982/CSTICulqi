package com.csticorp.culqi.test.presentation.composables

import androidx.compose.material3.LocalTextStyle
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
import androidx.compose.ui.unit.sp
import com.csticorp.culqi.test.domain.utils.fontWeight325
import com.csticorp.culqi.test.domain.utils.fontWeight350
import com.csticorp.culqi.test.domain.utils.fontWeight400
import com.csticorp.culqi.test.presentation.ui.theme.BlueGray700
import com.csticorp.culqi.test.presentation.ui.theme.BlueGray800
import com.csticorp.culqi.test.presentation.ui.theme.PoppinsMedium
import com.csticorp.culqi.test.presentation.ui.theme.PoppinsRegular

@Composable
fun Text12(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = BlueGray700,
    textAlign: TextAlign = TextAlign.Start,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    fontWeight: FontWeight = fontWeight325,
    lineHeight: TextUnit = 16.sp,
    fontFamily: FontFamily = PoppinsRegular,
    softWrap: Boolean = true,
    style: TextStyle = LocalTextStyle.current,
    onTextLayout: (TextLayoutResult) -> Unit = {},
) {
    BaseText(
        modifier = modifier,
        text = text,
        textAlign = textAlign,
        fontWeight = fontWeight,
        fontSize = 12.sp,
        lineHeight = lineHeight,
        color = color,
        minLines = minLines,
        maxLines = maxLines,
        fontFamily = fontFamily,
        softWrap = softWrap,
        style = style,
        onTextLayout = onTextLayout
    )
}

@Composable
fun Text12(
    modifier: Modifier = Modifier,
    text: AnnotatedString,
    color: Color = BlueGray700,
    textAlign: TextAlign = TextAlign.Start,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    fontWeight: FontWeight = fontWeight325,
    lineHeight: TextUnit = 16.sp,
    fontFamily: FontFamily = PoppinsRegular,
    softWrap: Boolean = true,
    style: TextStyle = LocalTextStyle.current,
    onTextLayout: (TextLayoutResult) -> Unit = {},
) {
    BaseText(
        modifier = modifier,
        text = text,
        textAlign = textAlign,
        fontWeight = fontWeight,
        fontSize = 12.sp,
        lineHeight = lineHeight,
        color = color,
        minLines = minLines,
        maxLines = maxLines,
        fontFamily = fontFamily,
        softWrap = softWrap,
        style = style,
        onTextLayout = onTextLayout
    )
}

@Composable
fun Text12Medium(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = BlueGray700,
    textAlign: TextAlign = TextAlign.Start,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    lineHeight: TextUnit = 16.sp,
    softWrap: Boolean = true,
    style: TextStyle = LocalTextStyle.current,
    onTextLayout: (TextLayoutResult) -> Unit = {},
) {
    BaseText(
        modifier = modifier,
        text = text,
        textAlign = textAlign,
        fontWeight = fontWeight350,
        fontSize = 12.sp,
        lineHeight = lineHeight,
        color = color,
        minLines = minLines,
        maxLines = maxLines,
        fontFamily = PoppinsMedium,
        softWrap = softWrap,
        style = style,
        onTextLayout = onTextLayout
    )
}

@Composable
fun TextNavMedium(
    modifier: Modifier = Modifier,
    text: String,
    fontSize: TextUnit,
    color: Color = BlueGray700,
    textAlign: TextAlign = TextAlign.Start,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    lineHeight: TextUnit = 16.sp,
    softWrap: Boolean = true,
    onTextLayout: (TextLayoutResult) -> Unit = {},
) {
    BaseText(
        modifier = modifier,
        text = text,
        textAlign = textAlign,
        fontWeight = fontWeight350,
        fontSize = fontSize,
        lineHeight = lineHeight,
        color = color,
        minLines = minLines,
        maxLines = maxLines,
        fontFamily = PoppinsMedium,
        softWrap = softWrap,
        onTextLayout = onTextLayout
    )
}

@Composable
fun Text12Book400(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = BlueGray800,
    textAlign: TextAlign = TextAlign.Start,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    lineHeight: TextUnit = 16.sp,
    softWrap: Boolean = true,
    style: TextStyle = LocalTextStyle.current,
    onTextLayout: (TextLayoutResult) -> Unit = {},
) {
    BaseText(
        modifier = modifier,
        text = text,
        textAlign = textAlign,
        fontWeight = fontWeight400,
        fontSize = 12.sp,
        lineHeight = lineHeight,
        color = color,
        minLines = minLines,
        maxLines = maxLines,
        fontFamily = PoppinsRegular,
        softWrap = softWrap,
        style = style,
        onTextLayout = onTextLayout
    )
}