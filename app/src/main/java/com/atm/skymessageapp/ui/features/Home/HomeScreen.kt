package com.atm.skymessageapp.ui.features.Home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Message
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.atm.skymessageapp.ui.components.CustomElevatedCard
import com.atm.skymessageapp.ui.components.ScreenContainer
import com.atm.skymessageapp.ui.theme.ColorBlack
import com.atm.skymessageapp.ui.theme.Typography

@Composable
fun HomeScreen(
    toSendMessages: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {

    ScreenContainer(
        modifier = Modifier.fillMaxSize()
    ) {
        CustomElevatedCard(
            modifier = Modifier.fillMaxWidth(),
            onClick = toSendMessages
        ) {
            Icon(
                modifier = Modifier.size(42.dp),
                imageVector = Icons.Default.Message,
                contentDescription = null,
                tint = ColorBlack
            )
            Text(
                text = "Mensajes",
                style = Typography.titleSmall
            )
            Text(
                text = "Prueba",
                style = Typography.bodyMedium
            )
        }
    }
}