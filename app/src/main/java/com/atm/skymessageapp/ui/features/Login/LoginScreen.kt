package com.atm.skymessageapp.ui.features.Login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
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

    ScreenContainer(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text(
            text = stringResource(R.string.app_name),
            style = Typography.titleLarge
        )

        AsyncImage(
            modifier = Modifier.fillMaxWidth(0.5f),
            model = "https://img.freepik.com/vector-gratis/ilustracion-concepto-inicio-sesion_114360-739.jpg",
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

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "V${BuildConfig.VERSION_NAME}",
            style = Typography.bodySmall
        )
    }

}