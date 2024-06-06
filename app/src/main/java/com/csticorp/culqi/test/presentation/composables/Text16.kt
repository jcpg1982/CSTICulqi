package com.csticorp.culqi.test.presentation.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.csticorp.culqi.test.domain.utils.fontWeight350
import com.csticorp.culqi.test.domain.utils.fontWeight400
import com.csticorp.culqi.test.presentation.ui.theme.BlueGray700
import com.csticorp.culqi.test.presentation.ui.theme.DynamicEighteenTextSize
import com.csticorp.culqi.test.presentation.ui.theme.DynamicTwentyFourTextSize
import com.csticorp.culqi.test.presentation.ui.theme.DynamicTwentyTextSize
import com.csticorp.culqi.test.presentation.ui.theme.PoppinsBold
import com.csticorp.culqi.test.presentation.ui.theme.PoppinsMedium

@Composable
fun Text16(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = BlueGray700,
    textAlign: TextAlign = TextAlign.Start,
    fontWeight: FontWeight = fontWeight350,
    lineHeight: TextUnit = DynamicTwentyTextSize,
    fontFamily: FontFamily = PoppinsMedium,
) {
    BaseText(
        modifier = modifier,
        text = text,
        textAlign = textAlign,
        fontWeight = fontWeight,
        fontSize = DynamicEighteenTextSize,
        lineHeight = lineHeight,
        color = color,
        fontFamily = fontFamily
    )
}

@Composable
fun Text16Bold(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = BlueGray700,
    textAlign: TextAlign = TextAlign.Start,
) {
    BaseText(
        modifier = modifier,
        text = text,
        textAlign = textAlign,
        fontWeight = fontWeight400,
        fontSize = DynamicEighteenTextSize,
        lineHeight = DynamicTwentyFourTextSize,
        color = color,
        fontFamily = PoppinsBold
    )
}