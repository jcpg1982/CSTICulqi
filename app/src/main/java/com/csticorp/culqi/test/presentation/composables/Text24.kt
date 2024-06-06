package com.csticorp.culqi.test.presentation.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.csticorp.culqi.test.domain.utils.fontWeight350
import com.csticorp.culqi.test.presentation.ui.theme.BlueGray700
import com.csticorp.culqi.test.presentation.ui.theme.PoppinsMedium

@Composable
fun Text24(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = BlueGray700,
    textAlign: TextAlign = TextAlign.Start,
    fontWeight: FontWeight = fontWeight350,
    fontFamily: FontFamily = PoppinsMedium
) {
    BaseText(
        modifier = modifier,
        text = text,
        textAlign = textAlign,
        fontWeight = fontWeight,
        fontSize = 24.sp,
        lineHeight = 30.sp,
        color = color,
        fontFamily = fontFamily,
    )
}