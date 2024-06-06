package com.csticorp.culqi.test.presentation.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.csticorp.culqi.test.R
import com.csticorp.culqi.test.presentation.ui.theme.ColorWhite
import com.csticorp.culqi.test.presentation.ui.theme.ContentInset
import com.csticorp.culqi.test.presentation.ui.theme.ContentInsetFour
import com.csticorp.culqi.test.presentation.ui.theme.ContentInsetOneHundred
import com.csticorp.culqi.test.presentation.ui.theme.ContentInsetOneHundredFifty
import com.csticorp.culqi.test.presentation.ui.theme.DynamicEighteenTextSize
import com.csticorp.culqi.test.presentation.ui.theme.Turquoise500


@Composable
fun DialogLoading(
    message: String = "Loading"
) {
    Dialog(
        onDismissRequest = { }, properties = DialogProperties(
            dismissOnBackPress = false, dismissOnClickOutside = false
        )
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(modifier = Modifier.wrapContentSize()) {
                /*CircularProgressIndicator(
                    color = Turquoise500,
                    strokeWidth = ContentInsetFour,
                    modifier = Modifier
                        .size(ContentInsetOneHundredFifty)
                        .align(Alignment.Center)
                )
                Image(
                    imageVector = Icons.Filled.Refresh,
                    contentDescription = "Image Loading",
                    modifier = Modifier
                        .size(ContentInsetOneHundred)
                        .align(Alignment.Center),
                    alignment = Alignment.Center
                )*/
            }

            Text(
                text = message,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = ContentInset, end = ContentInset, top = ContentInset)
                    .align(Alignment.CenterHorizontally),
                color = ColorWhite,
                fontSize = DynamicEighteenTextSize,
                textAlign = TextAlign.Center
            )
        }
    }
}