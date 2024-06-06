package com.csticorp.culqi.test.domain.utils

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.ui.graphics.Color
import com.google.gson.Gson
import java.net.URLDecoder
import java.net.URLEncoder

object Utils {

    val getEnterTransition: EnterTransition
        get() {
            val fastOutSlowIn = FastOutSlowInEasing
            return slideInHorizontally(
                initialOffsetX = { 1000 }, animationSpec = tween(700, easing = fastOutSlowIn)
            ) + fadeIn(
                initialAlpha = 0.3f, animationSpec = tween(700, easing = fastOutSlowIn)
            ) + scaleIn(
                initialScale = 0.9f, animationSpec = tween(700, easing = fastOutSlowIn)
            )
        }

    val getExitTransition: ExitTransition
        get() {
            val fastOutSlowIn = FastOutSlowInEasing
            return slideOutHorizontally(
                targetOffsetX = { -1000 }, animationSpec = tween(700, easing = fastOutSlowIn)
            ) + fadeOut(
                targetAlpha = 0.3f, animationSpec = tween(700, easing = fastOutSlowIn)
            ) + scaleOut(
                targetScale = 0.9f, animationSpec = tween(700, easing = fastOutSlowIn)
            )
        }

    val <T> T?.objectToString: String
        get() = if (this != null) {
            val text = Gson().toJson(this)
            val encode = URLEncoder.encode(text, "UTF-8")
            encode
        } else ""

    fun <T> String?.stringToObject(clase: Class<T>): T? {
        if (isNullOrEmpty()) return null
        return try {
            val decode = URLDecoder.decode(this, "UTF-8")
            Gson().fromJson(decode, clase)
        } catch (e: Exception) {
            null
        }
    }

    val shimmerColors
        get() = listOf(
            Color.LightGray.copy(alpha = 0.6f),
            Color.LightGray.copy(alpha = 0.2f),
            Color.LightGray.copy(alpha = 0.6f)
        )
}