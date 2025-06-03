package com.atm.skymessageapp.ui.features.SendMessages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.atm.skymessageapp.ui.components.ScreenContainer

@Composable
fun SendMessagesScreen(
    userId: Int,
    viewModel: SendMessagesViewModel = hiltViewModel()
) {


    LaunchedEffect(
        key1 = Unit
    ) {
        viewModel.init()
    }

    val messages = viewModel.mensajes.collectAsState().value

    ScreenContainer(
        modifier = Modifier.fillMaxSize()
    ) {
        Text("Messages $userId")
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            items(messages) {
                Text(it.second)
            }
        }
    }
}