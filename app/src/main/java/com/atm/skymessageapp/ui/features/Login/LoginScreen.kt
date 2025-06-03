package com.atm.skymessageapp.ui.features.Login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.atm.skymessageapp.BuildConfig
import com.atm.skymessageapp.R
import com.atm.skymessageapp.ui.components.CustomButton
import com.atm.skymessageapp.ui.components.CustomOutlineTextField
import com.atm.skymessageapp.ui.components.ScreenContainer
import com.atm.skymessageapp.ui.theme.Typography

@Composable
fun LoginScreen(
    toHome: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
) {

    val focusManager = LocalFocusManager.current

    val state = viewModel.state
    val components = viewModel.components

    LaunchedEffect(
        key1 = components.isSuccessLogin,
    ) {
        if (components.isSuccessLogin) {
            toHome()
            viewModel.onEvent(LoginEvent.ClearSuccessLogin)
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        ScreenContainer(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            error = components.error,
            dismissError = {
                viewModel.onEvent(LoginEvent.ClearError)
            }
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Icon(
                    modifier = Modifier.size(32.dp),
                    painter = painterResource(R.drawable.sky_message),
                    contentDescription = null
                )
                Text(
                    text = stringResource(R.string.app_name),
                    style = Typography.titleLarge
                )
            }

            Image(
                modifier = Modifier.fillMaxWidth(0.6f),
                painter = painterResource(R.drawable.login),
                contentDescription = null,
                contentScale = ContentScale.FillWidth
            )

            Text(
                text = stringResource(R.string.login_title),
                style = Typography.titleMedium
            )

            CustomOutlineTextField(
                modifier = Modifier.fillMaxWidth(),
                value = state.user,
                onValueChange = {
                    viewModel.onEvent(LoginEvent.SetUser(it))
                },
                label = stringResource(R.string.login_user),
                imeAction = ImeAction.Next,
                keyboardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                ),
                error = components.errorUser
            )

            CustomOutlineTextField(
                modifier = Modifier.fillMaxWidth(),
                value = state.password,
                onValueChange = {
                    viewModel.onEvent(LoginEvent.SetPassword(it))
                },
                label = stringResource(R.string.login_password),
                imeAction = ImeAction.Done,
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.clearFocus()
                        viewModel.onEvent(LoginEvent.Login)
                    }
                ),
                trailingIcon = {
                    IconButton(
                        onClick = {
                            viewModel.onEvent(LoginEvent.TogglePasswordVisibility)
                        }
                    ) {
                        Icon(
                            imageVector = if (components.isVisiblePassword) Icons.Default.VisibilityOff
                            else Icons.Default.Visibility,
                            contentDescription = null
                        )
                    }
                },
                error = components.errorPassword,
                visualTransformation = if (components.isVisiblePassword) VisualTransformation.None else PasswordVisualTransformation()
            )

            CustomButton(
                label = stringResource(R.string.login_btn),
                isLoading = components.isLoading,
                onClick = {
                    viewModel.onEvent(LoginEvent.Login)
                },
            )

        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "V${BuildConfig.VERSION_NAME}",
                style = Typography.bodySmall
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(
                    text = "Developed by",
                    style = Typography.bodySmall
                )
                AsyncImage(
                    modifier = Modifier.height(12.dp),
                    model = R.drawable.atm,
                    contentDescription = null,
                    contentScale = ContentScale.FillHeight
                )
            }
        }
    }

}