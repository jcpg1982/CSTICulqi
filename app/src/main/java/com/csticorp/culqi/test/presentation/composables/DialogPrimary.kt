package com.csticorp.culqi.test.presentation.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.csticorp.culqi.test.R
import com.csticorp.culqi.test.presentation.ui.theme.BlueGray700

@Composable
fun DialogPrimary(
    setShowDialog: (Boolean) -> Unit,
    dialogCallback: DialogCallback,
    primaryTitleText: String,
    secondaryTitleText: String? = null,
    primaryBodyText: String? = null,
    secondaryBodyText: String? = null,
    primaryButtonText: String? = null,
    secondaryButtonText: String? = null,
    textClickable: String? = null,
    primaryBody: (@Composable () -> Unit)? = null
) {

    Dialog(
        onDismissRequest = { setShowDialog(false) },
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false
        )
    ) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = Color.White
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 24.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Icon(
                    modifier = Modifier
                        .align(Alignment.End)
                        .clickable {
                            dialogCallback.onDismmissClick(false)
                        },
                    painter = painterResource(id = R.drawable.ic_cancel),
                    contentDescription = null,
                )

                Spacer(modifier = Modifier.size(24.dp))

                Text14Medium(
                    modifier = Modifier.fillMaxWidth(),
                    text = primaryTitleText,
                    color = BlueGray700,
                    textAlign = TextAlign.Center,
                )

                secondaryTitleText?.let {
                    Spacer(modifier = Modifier.size(16.dp))

                    Text14(
                        modifier = Modifier.fillMaxWidth(),
                        text = secondaryTitleText,
                        color = BlueGray700,
                        textAlign = TextAlign.Center,
                    )
                }

                primaryBody?.let {
                    Spacer(modifier = Modifier.size(16.dp))
                    primaryBody.invoke()
                }

                primaryBodyText?.let {
                    Spacer(modifier = Modifier.size(16.dp))

                    Text12(
                        modifier = Modifier.fillMaxWidth(),
                        text = primaryBodyText,
                        color = BlueGray700,
                        textAlign = TextAlign.Center,
                    )
                }

                secondaryBodyText?.let {
                    Spacer(modifier = Modifier.size(16.dp))

                    Text12(
                        modifier = Modifier.fillMaxWidth(),
                        text = secondaryBodyText,
                        color = BlueGray700,
                        textAlign = TextAlign.Center,
                    )
                }

                primaryButtonText?.let {
                    Spacer(modifier = Modifier.size(16.dp))

                    PrimaryButton(
                        modifier = Modifier.fillMaxWidth(),
                        text = primaryButtonText,
                        onClick = { dialogCallback.onPrimaryButtonClick(false) }
                    )
                }

                secondaryButtonText?.let {
                    Spacer(modifier = Modifier.size(16.dp))

                    PrimaryButton(
                        modifier = Modifier.fillMaxWidth(),
                        text = secondaryButtonText,
                        onClick = { dialogCallback.onSecondaryButtonClick(false) },
                        isOutline = true
                    )
                }

                textClickable?.let {
                    TextClickable14(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.CenterHorizontally),
                        text = textClickable,
                        textDecoration = TextDecoration.None,
                        onClick = { dialogCallback.onTextClickableClick(false) },
                        textHorizontalArrangement = Arrangement.Center
                    )
                }
            }
        }
    }
}