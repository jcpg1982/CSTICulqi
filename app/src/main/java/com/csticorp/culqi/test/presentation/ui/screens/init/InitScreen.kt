package com.csticorp.culqi.test.presentation.ui.screens.init

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.csticorp.culqi.test.R
import com.csticorp.culqi.test.data.model.Routes
import com.csticorp.culqi.test.data.response.DataUser
import com.csticorp.culqi.test.presentation.composables.EmailTextField
import com.csticorp.culqi.test.presentation.composables.PrimaryButton
import com.csticorp.culqi.test.presentation.composables.PrimaryButtonWithIcon
import com.csticorp.culqi.test.presentation.composables.Spacer16
import com.csticorp.culqi.test.presentation.composables.Spacer8
import com.csticorp.culqi.test.presentation.composables.Text16
import com.csticorp.culqi.test.presentation.composables.Text24
import com.csticorp.culqi.test.presentation.composables.TextClickable
import com.csticorp.culqi.test.presentation.ui.theme.ColorWhite
import com.csticorp.culqi.test.presentation.ui.theme.ContentInset
import com.csticorp.culqi.test.presentation.ui.theme.ContentInsetThirty
import com.csticorp.culqi.test.presentation.ui.theme.OverlayLight
import com.csticorp.culqi.test.presentation.ui.theme.Turquoise500
import com.csticorp.culqi.test.presentation.viewModels.NavigationViewModel

@Composable
fun InitScreen(
    navigationViewModel: NavigationViewModel, initViewModels: InitViewModels = hiltViewModel()
) {
    val getAllUsers by initViewModels.getAllUsers.collectAsState()
    val email by initViewModels.email.collectAsState()
    val isEnabledButton by initViewModels.isEnabledButton.collectAsState()

    var dataList by remember { mutableStateOf(value = listOf<DataUser>()) }

    LaunchedEffect(getAllUsers) {
        when (val state = getAllUsers) {
            is InitScreenState.Loading -> {}
            is InitScreenState.Error -> {}
            is InitScreenState.Success -> dataList = state.dataList
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {

        val image = painterResource(id = R.drawable.background)
        Box(
            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            Image(
                painter = image,
                contentDescription = null,
                modifier = Modifier
                    .padding(top = ContentInset)
                    .fillMaxWidth(fraction = 0.8f)
                    .fillMaxHeight(fraction = 0.7f)
                    .align(Alignment.TopEnd),
                contentScale = ContentScale.Crop,
                alpha = 0.4f
            )

            Column(
                modifier = Modifier
                    .padding(start = ContentInset, end = ContentInset, bottom = ContentInset)
                    .align(Alignment.BottomCenter)
            ) {

                Text24(text = "Hi!", color = Color.White)

                Column(
                    modifier = Modifier
                        .padding(top = ContentInsetThirty)
                        .background(
                            color = OverlayLight, shape = RoundedCornerShape(8.dp)
                        )
                        .fillMaxWidth()
                        .padding(all = ContentInset)
                ) {
                    Spacer16()

                    EmailTextField(
                        text = email,
                        onValueChange = { initViewModels.setEmail(it) },
                        modifier = Modifier.background(
                            color = Color.White, shape = RoundedCornerShape(8.dp)
                        )
                    )

                    Spacer8()

                    PrimaryButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        text = "Continue",
                        onClick = {
                            navigationViewModel.onNavigateTo(
                                Routes.SignUpScreen().createNewRoute(email = email)
                            )
                        },
                        isEnabled = isEnabledButton
                    )

                    Spacer8()

                    Text16(
                        text = "OR",
                        modifier = Modifier
                            .fillMaxWidth(),
                        color = ColorWhite,
                        textAlign = TextAlign.Center
                    )

                    Spacer8()

                    PrimaryButtonWithIcon(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        text = "Continue with Facebook",
                        onClick = { },
                        icon = R.drawable.ic_facebook
                    )

                    Spacer8()

                    PrimaryButtonWithIcon(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        text = "Continue with Google",
                        onClick = { },
                        icon = R.drawable.ic_google
                    )

                    Spacer8()

                    PrimaryButtonWithIcon(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        text = "Continue with Apple",
                        onClick = { },
                        icon = R.drawable.ic_apple
                    )

                    Spacer8()

                    TextClickable(
                        fullText = "Don't have an account? Sign up",
                        clickableText = "Sign up",
                        clickableTextColor = Turquoise500
                    ) { }

                    Spacer8()

                    Text16(
                        text = "Forgot your password?",
                        modifier = Modifier
                            .fillMaxWidth(),
                        color = Turquoise500
                    )

                    Spacer16()
                }
            }
        }

    }
}