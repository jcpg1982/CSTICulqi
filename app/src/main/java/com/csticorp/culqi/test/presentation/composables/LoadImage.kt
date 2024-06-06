package com.csticorp.culqi.test.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.csticorp.culqi.test.presentation.ui.theme.BlueGray300
import com.csticorp.culqi.test.presentation.ui.theme.ColorWhite
import com.csticorp.culqi.test.presentation.ui.theme.ContentInsetThirty

@Composable
fun LoadImageWithURL(
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop,
    photoUrl: String,
    letter: String?
) {
    val context = LocalContext.current
    val density = LocalDensity.current.density
    var widthInDp by rememberSaveable { mutableFloatStateOf(ContentInsetThirty.value) }
    SubcomposeAsyncImage(
        model = ImageRequest.Builder(context).data(photoUrl).crossfade(true)
            .decoderFactory(SvgDecoder.Factory()).build(),
        contentDescription = "",
        modifier = modifier.onGloballyPositioned { coordinates ->
            val size = coordinates.size
            val widthInFloat = size.width.toFloat()
            widthInDp = widthInFloat / density
        },
        loading = {
            CircularProgressIndicator(
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.Center)
            )
        },
        error = {
            CircleWithLetter(letter = letter ?: "JM", size = widthInDp.dp)
        },
        success = {
            SubcomposeAsyncImageContent()
        },
        alignment = Alignment.Center,
        contentScale = contentScale
    )
}

@Composable
fun CircleWithLetter(
    letter: String,
    size: Dp,
) {
    val fontSize = size.value / 2
    Box(
        modifier = Modifier
            .size(size)
            .background(color = BlueGray300, shape = CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = letter,
            fontSize = fontSize.sp,
            color = ColorWhite,
            fontWeight = FontWeight.Bold
        )

    }
}