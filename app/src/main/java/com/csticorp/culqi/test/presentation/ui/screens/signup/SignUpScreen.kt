package com.csticorp.culqi.test.presentation.ui.screens.signup

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
import com.csticorp.culqi.test.presentation.composables.SingleLineTextField
import com.csticorp.culqi.test.presentation.composables.Spacer16
import com.csticorp.culqi.test.presentation.composables.Text24
import com.csticorp.culqi.test.presentation.composables.TextClickable
import com.csticorp.culqi.test.presentation.ui.theme.ContentInset
import com.csticorp.culqi.test.presentation.ui.theme.ContentInsetThirty
import com.csticorp.culqi.test.presentation.ui.theme.OverlayLight
import com.csticorp.culqi.test.presentation.ui.theme.Turquoise500
import com.csticorp.culqi.test.presentation.viewModels.NavigationViewModel

@Composable
fun SignUpScreen(
    navigationViewModel: NavigationViewModel,
    signUpViewModels: SignUpViewModels = hiltViewModel()
) {
    val dataUser by signUpViewModels.dataUser.collectAsState()
    val email by signUpViewModels.email.collectAsState()
    val fullName by signUpViewModels.fullName.collectAsState()
    val password by signUpViewModels.password.collectAsState()
    val isEnabledButton by signUpViewModels.isEnabledButton.collectAsState()
    val registerUser by signUpViewModels.registerUser.collectAsState()

    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(value = false) }
    var messageError by remember { mutableStateOf(value = "") }

    LaunchedEffect(registerUser) {
        when (val state = registerUser) {
            is SignUpScreenState.Error -> {
                isLoading = false
                messageError = state.error.message.orEmpty()
            }

            is SignUpScreenState.Loading -> {
                isLoading = state.isLoading
                messageError = ""
            }

            is SignUpScreenState.Success -> {
                isLoading = false
                navigationViewModel.onNavigateTo(
                    Routes.LoginScreen().createNewRoute(dataUser.objectToString)
                )
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

                Text24(text = "Sign up", color = Color.White)

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

                    Text24(
                        text = "Looks like you don't have an account, Let's create a new account for $email",
                        color = Color.White
                    )

                    Spacer16()

                    SingleLineTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = Color.White, shape = RoundedCornerShape(8.dp)),
                        text = fullName,
                        onValueChange = { signUpViewModels.setFullName(it) },
                        labelText = stringResource(id = R.string.name)
                    )

                    Spacer16()

                    PasswordTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = Color.White, shape = RoundedCornerShape(8.dp)),
                        text = password,
                        onValueChange = { signUpViewModels.setPassword(it) },
                        labelText = stringResource(id = R.string.password),
                        isPasswordVisible = passwordVisible,
                        onPasswordVisibleValueChange = { passwordVisible = !passwordVisible },
                    )

                    Spacer16()

                    TextClickable(
                        fullText = "By selecting Agree and continue below, I agree to Terms of Service and Privacy Policy",
                        clickableText = "Terms of Service and Privacy Policy",
                        clickableTextColor = Turquoise500
                    ) {

                    }

                    Spacer16()

                    PrimaryButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        text = "Agree and Continue",
                        onClick = { signUpViewModels.registerUser() },
                        isEnabled = isEnabledButton
                    )

                    Spacer16()
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
}