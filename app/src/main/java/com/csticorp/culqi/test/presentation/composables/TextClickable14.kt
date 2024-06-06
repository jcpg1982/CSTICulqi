package com.csticorp.culqi.test.presentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.csticorp.culqi.test.domain.utils.fontWeight350
import com.csticorp.culqi.test.presentation.ui.theme.PoppinsMedium
import com.csticorp.culqi.test.presentation.ui.theme.Turquoise500

@Composable
fun TextClickable14(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = Turquoise500,
    fontWeight: FontWeight = fontWeight350,
    fontFamily: FontFamily = PoppinsMedium,
    textDecoration: TextDecoration = TextDecoration.None,
    leadingIcon: Int? = null,
    leadingColorIcon: Color? = null,
    trailingIcon: Int? = null,
    trailingColorIcon: Color? = null,
    onClick: () -> Unit,
    textHorizontalArrangement: Arrangement.Horizontal = Arrangement.Start
) {
    val annotatedString = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                textDecoration = textDecoration,
                color = color,
                fontSize = 14.sp,
                fontWeight = fontWeight,
                fontFamily = fontFamily,
            )
        ) {
            append(text)
        }
        addStringAnnotation(
            tag = "clickable",
            annotation = "true",
            start = 0,
            end = text.length
        )
    }

    Row(
        modifier = modifier,
        horizontalArrangement = textHorizontalArrangement,
        verticalAlignment = Alignment.CenterVertically
    ) {
        leadingIcon?.let {
            Icon(
                painter = painterResource(id = leadingIcon),
                contentDescription = null,
                tint = leadingColorIcon ?: Color.Unspecified,
            )
            Spacer(modifier = Modifier.size(6.dp))
        }

        ClickableText(
            modifier = Modifier,
            text = annotatedString,
            onClick = { offset ->
                annotatedString.getStringAnnotations(
                    tag = "clickable",
                    start = offset,
                    end = offset
                ).firstOrNull()?.let { annotation ->
                    if (annotation.item == "true") {
                        onClick()
                    }
                }
            }
        )

        trailingIcon?.let {
            Spacer(modifier = Modifier.size(6.dp))
            Icon(
                painter = painterResource(id = trailingIcon),
                contentDescription = null,
                tint = trailingColorIcon ?: Color.Unspecified,
            )
        }
    }
}