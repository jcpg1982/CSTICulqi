package com.csticorp.culqi.test.presentation.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.csticorp.culqi.test.presentation.ui.theme.BlueGray100
import com.csticorp.culqi.test.presentation.ui.theme.ColorBlack

@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    shape: Shape = RoundedCornerShape(8.dp),
    icon: ImageVector? = null,
    iconColor: Color = Color.White,
    isOutline: Boolean = false,
    isEnabled: Boolean = true,
    iconOnRight: Boolean = true
) {
    val scheme = MaterialTheme.colorScheme
    val buttonColor = ButtonDefaults.buttonColors(
        containerColor = if (isOutline) scheme.secondary else scheme.primary,
        disabledContainerColor = BlueGray100,
        disabledContentColor = ColorBlack
    )

    val textColor = if (isEnabled) {
        if (isOutline) scheme.primary else Color.White
    } else ColorBlack

    if (isOutline) {
        OutlinedButton(
            modifier = modifier,
            onClick = onClick,
            colors = buttonColor,
            shape = shape,
            border = BorderStroke(1.dp, scheme.primary),
            enabled = isEnabled
        ) {
            if (icon != null && !iconOnRight) {
                Icon(imageVector = icon, contentDescription = null, tint = iconColor)
                Spacer(modifier = Modifier.size(8.dp))
            }
            Text16(
                text = text, color = textColor
            )
            if (icon != null && iconOnRight) {
                Spacer(modifier = Modifier.size(8.dp))
                Icon(imageVector = icon, contentDescription = null, tint = iconColor)
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
            if (icon != null && !iconOnRight) {
                Icon(imageVector = icon, contentDescription = null, tint = iconColor)
                Spacer(modifier = Modifier.size(8.dp))
            }
            Text16(
                text = text, color = textColor
            )
            if (icon != null && iconOnRight) {
                Spacer(modifier = Modifier.size(8.dp))
                Icon(imageVector = icon, contentDescription = null, tint = iconColor)
            }
        }
    }
}