package com.csticorp.culqi.test.presentation.composables

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.csticorp.culqi.test.presentation.ui.theme.ContentInsetEight
import com.csticorp.culqi.test.presentation.ui.theme.ContentInsetEightTeen
import com.csticorp.culqi.test.presentation.ui.theme.ContentInsetFour
import com.csticorp.culqi.test.presentation.ui.theme.ContentInsetFourteen
import com.csticorp.culqi.test.presentation.ui.theme.ContentInsetSix
import com.csticorp.culqi.test.presentation.ui.theme.ContentInsetTen
import com.csticorp.culqi.test.presentation.ui.theme.ContentInsetTwelve

@Composable
fun Spacer0() {
    Spacer(modifier = Modifier.size(0.dp))
}

@Composable
fun Spacer4() {
    Spacer(modifier = Modifier.size(ContentInsetFour))
}

@Composable
fun Spacer6() {
    Spacer(modifier = Modifier.size(ContentInsetSix))
}

@Composable
fun Spacer8() {
    Spacer(modifier = Modifier.size(ContentInsetEight))
}

@Composable
fun Spacer10() {
    Spacer(modifier = Modifier.size(ContentInsetTen))
}

@Composable
fun Spacer12() {
    Spacer(modifier = Modifier.size(ContentInsetTwelve))
}

@Composable
fun Spacer14() {
    Spacer(modifier = Modifier.size(ContentInsetFourteen))
}

@Composable
fun Spacer16() {
    Spacer(modifier = Modifier.size(ContentInsetEightTeen))
}