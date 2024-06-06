package com.csticorp.culqi.test.presentation.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.csticorp.culqi.test.domain.utils.fontWeight325
import com.csticorp.culqi.test.domain.utils.fontWeight350
import com.csticorp.culqi.test.presentation.ui.theme.BlueGray700
import com.csticorp.culqi.test.presentation.ui.theme.PoppinsMedium
import com.csticorp.culqi.test.presentation.ui.theme.PoppinsRegular

@Composable
fun Text14(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = BlueGray700,
    textAlign: TextAlign = TextAlign.Start,
    lineHeight: TextUnit = 18.sp,
    fontWeight: FontWeight = fontWeight325,
    fontFamily: FontFamily = PoppinsRegular
) {
    BaseText(
        modifier = modifier,
        text = text,
        textAlign = textAlign,
        fontWeight = fontWeight,
        fontSize = 14.sp,
        lineHeight = lineHeight,
        color = color,
        fontFamily = fontFamily
    )
}

@Composable
fun Text14Medium(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = BlueGray700,
    textAlign: TextAlign = TextAlign.Start,
    lineHeight: TextUnit = 18.sp,
) {
    BaseText(
        modifier = modifier,
        text = text,
        textAlign = textAlign,
        fontWeight = fontWeight350,
        fontSize = 14.sp,
        lineHeight = lineHeight,
        color = color,
        fontFamily = PoppinsMedium,
    )
}