package com.csticorp.culqi.test.presentation.composables

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.csticorp.culqi.test.presentation.ui.theme.BlueGray100
import com.csticorp.culqi.test.presentation.ui.theme.BlueGray50
import com.csticorp.culqi.test.presentation.ui.theme.BlueGray600
import com.csticorp.culqi.test.presentation.ui.theme.BlueGray700
import com.csticorp.culqi.test.presentation.ui.theme.ColorBlack
import com.csticorp.culqi.test.presentation.ui.theme.ColorWhite

@Composable
fun PrimaryButtonWithIcon(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    shape: Shape = RoundedCornerShape(8.dp),
    @DrawableRes icon: Int? = null,
    isOutline: Boolean = false,
    isEnabled: Boolean = true
) {
    val buttonColor = ButtonDefaults.buttonColors(
        containerColor = ColorWhite,
        contentColor = BlueGray600,
        disabledContainerColor = BlueGray100,
        disabledContentColor = ColorBlack
    )

    val textColor = if (isEnabled) ColorBlack else ColorWhite

    if (isOutline) {
        OutlinedButton(
            modifier = modifier,
            onClick = onClick,
            colors = buttonColor,
            shape = shape,
            border = BorderStroke(1.dp, BlueGray50),
            enabled = isEnabled
        ) {
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterStart) {
                if (icon != null) {
                    Image(painter = painterResource(id = icon), contentDescription = null)
                    Spacer(modifier = Modifier.size(8.dp))
                }
                Text16(
                    text = text, color = textColor, modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    } else {
        Button(
            modifier = modifier,
            onClick = onClick,
            colors = buttonColor,
            shape = shape,
            enabled = isEnabled
        ) {
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterStart) {
                if (icon != null) {
                    Image(painter = painterResource(id = icon), contentDescription = null)
                    Spacer(modifier = Modifier.size(8.dp))
                }
                Text16(
                    text = text, color = textColor, modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}