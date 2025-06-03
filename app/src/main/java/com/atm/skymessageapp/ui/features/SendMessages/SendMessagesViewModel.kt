package com.atm.skymessageapp.ui.features.SendMessages

import androidx.lifecycle.ViewModel
import com.atm.skymessageapp.domain.states.SocketConnectionState
import com.atm.skymessageapp.domain.repository.SocketRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SendMessagesViewModel @Inject constructor(
    private val repository: SocketRepository
) : ViewModel() {

    var isConnected: StateFlow<SocketConnectionState> = repository.isConnected

    fun connect() {
        repository.connect()
    }

    fun disconnect() {
        repository.disconnect()
    }
}