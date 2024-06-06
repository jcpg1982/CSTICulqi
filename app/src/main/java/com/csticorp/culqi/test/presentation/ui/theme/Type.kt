package com.csticorp.culqi.test.presentation.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import com.csticorp.culqi.test.R
import com.csticorp.culqi.test.domain.utils.fontWeight325
import com.csticorp.culqi.test.domain.utils.fontWeight350

val PoppinsRegular = FontFamily(
    Font(R.font.poppins_regular)
)

val PoppinsMedium = FontFamily(
    Font(R.font.poppins_medium)
)

val PoppinsBold = FontFamily(
    Font(R.font.poppins_bold),
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)

val textStyle14 = TextStyle(
    fontFamily = PoppinsRegular,
    fontSize = DynamicFourteenTextSize,
    lineHeight = DynamicEighteenTextSize,
    fontWeight = fontWeight325,
    color = BlueGray600,
    textDecoration = TextDecoration.None,
)

val textStyle24 = TextStyle(
    fontFamily = PoppinsRegular,
    fontSize = DynamicTwentyFourTextSize,
    lineHeight = DynamicThirtyTextSize,
    fontWeight = fontWeight350,
    color = BlueGray600,
    textDecoration = TextDecoration.None,
)