package com.atm.skymessageapp.domain.repository

import com.atm.skymessageapp.domain.states.SocketConnectionState
import kotlinx.coroutines.flow.StateFlow

interface SocketRepository {
    fun connect()
    fun disconnect()
    val isConnected: StateFlow<SocketConnectionState>
}