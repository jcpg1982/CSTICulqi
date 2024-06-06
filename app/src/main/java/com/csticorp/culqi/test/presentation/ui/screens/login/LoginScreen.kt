package com.csticorp.culqi.test.presentation.ui.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.csticorp.culqi.test.R
import com.csticorp.culqi.test.data.model.Routes
import com.csticorp.culqi.test.domain.utils.Utils.objectToString
import com.csticorp.culqi.test.presentation.composables.DialogCallback
import com.csticorp.culqi.test.presentation.composables.DialogLoading
import com.csticorp.culqi.test.presentation.composables.DialogPrimary
import com.csticorp.culqi.test.presentation.composables.PasswordTextField
import com.csticorp.culqi.test.presentation.composables.PrimaryButton
import com.csticorp.culqi.test.presentation.composables.Spacer16
import com.csticorp.culqi.test.presentation.composables.Text16
import com.csticorp.culqi.test.presentation.composables.Text24
import com.csticorp.culqi.test.presentation.ui.theme.ContentInset
import com.csticorp.culqi.test.presentation.ui.theme.ContentInsetThirty
import com.csticorp.culqi.test.presentation.ui.theme.OverlayLight
import com.csticorp.culqi.test.presentation.ui.theme.Turquoise500
import com.csticorp.culqi.test.presentation.viewModels.NavigationViewModel

@Composable
fun LoginScreen(
    navigationViewModel: NavigationViewModel, loginViewModels: LoginViewModels = hiltViewModel()
) {
    val dataUser by loginViewModels.dataUser.collectAsState()
    val password by loginViewModels.password.collectAsState()
    val isEnabledButton by loginViewModels.isEnabledButton.collectAsState()
    val loginUser by loginViewModels.loginUser.collectAsState()

    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(value = false) }
    var messageError by remember { mutableStateOf(value = "") }

    LaunchedEffect(loginUser) {
        when (val state = loginUser) {
            is LoginScreenState.Loading -> {
                isLoading = state.isLoading
                messageError = ""
            }

            is LoginScreenState.Error -> {
                isLoading = false
                messageError = state.error.message.orEmpty()
            }

            is LoginScreenState.Success -> {
                isLoading = false
                navigationViewModel.onNavigateTo(Routes.Home)
            }
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
                    .padding(
                        start = ContentInset, end = ContentInset, bottom = ContentInset
                    )
                    .align(Alignment.Center)
            ) {

                Text24(text = "Log in", color = Color.White)

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

                    Row(modifier = Modifier.fillMaxWidth()) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text24(
                                text = dataUser?.fullName.orEmpty(), color = Color.White
                            )
                            Text16(
                                text = dataUser?.email.orEmpty(), color = Color.White
                            )
                        }

                    }

                    Spacer16()

                    PasswordTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = Color.White, shape = RoundedCornerShape(8.dp)),
                        text = password,
                        onValueChange = { loginViewModels.setPassword(it) },
                        labelText = stringResource(id = R.string.password),
                        isPasswordVisible = passwordVisible,
                        onPasswordVisibleValueChange = { passwordVisible = !passwordVisible },
                    )

                    Spacer16()

                    PrimaryButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        text = "Continue",
                        onClick = { loginViewModels.loginUser() },
                        isEnabled = isEnabledButton
                    )

                    Spacer16()

                    Text16(
                        text = "Forgot your password?",
                        modifier = Modifier.fillMaxWidth(),
                        color = Turquoise500
                    )
                }
            }

        }
    }

    if (isLoading) {
        DialogLoading()
    }

    if (messageError.isNotEmpty()) {
        DialogPrimary(
            setShowDialog = {
                messageError = ""
            },
            dialogCallback = DialogCallback({
                messageError = ""
            }, {}, {}, {
                messageError = ""
            }),
            primaryButtonText = stringResource(id = R.string.accept),
            primaryTitleText = stringResource(id = R.string.we_are_sorry),
            primaryBodyText = messageError
        )
    }
}