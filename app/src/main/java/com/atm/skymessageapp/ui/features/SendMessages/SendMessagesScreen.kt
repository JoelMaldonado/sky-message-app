package com.atm.skymessageapp.ui.features.SendMessages

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.atm.skymessageapp.domain.states.SocketConnectionState
import com.atm.skymessageapp.ui.components.CustomButton
import com.atm.skymessageapp.ui.components.ScreenContainer

@Composable
fun SendMessagesScreen(
    userId: Int,
    viewModel: SendMessagesViewModel = hiltViewModel()
) {
    val connectionState = viewModel.isConnected.collectAsState()

    DisposableEffect(Unit) {
        Log.d("tagito", "Iniciando conexión al socket")
        onDispose {
            Log.d("tagito", "Desconectando del socket")
        }
    }

    ScreenContainer(
        modifier = Modifier.fillMaxSize()
    ) {
        when (connectionState.value) {
            SocketConnectionState.Connected -> {
                CustomButton(
                    label = "Desconectar",
                    onClick = viewModel::disconnect
                )
            }

            SocketConnectionState.Disconnected -> {
                CustomButton(
                    label = "Conectar",
                    onClick = viewModel::connect
                )
            }

            SocketConnectionState.Connecting -> Text("Conectando...")
            SocketConnectionState.Error -> Text("Error al conectar")
        }
    }
}