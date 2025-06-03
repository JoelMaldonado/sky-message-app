package com.atm.skymessageapp.data.repository

import com.atm.skymessageapp.domain.states.SocketConnectionState
import com.atm.skymessageapp.domain.repository.SocketRepository
import io.socket.client.IO
import io.socket.client.Socket
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.json.JSONObject
import javax.inject.Inject

class SocketRepositoryImpl @Inject constructor(
    private val socketUrl: String
) : SocketRepository {


    private val _connectionState = MutableStateFlow(SocketConnectionState.Disconnected)
    override val isConnected: StateFlow<SocketConnectionState> = _connectionState

    private var socket: Socket? = null

    override fun connect() {
        try {
            val options = IO.Options().apply {
                transports = arrayOf("websocket")
                reconnection = true
            }
            socket = IO.socket("http://192.168.68.105:3000", options)

            _connectionState.value = SocketConnectionState.Connecting

            socket?.on(Socket.EVENT_CONNECT) {
                _connectionState.value = SocketConnectionState.Connected
                val json = JSONObject()
                json.put("id", "Android S10+")
                socket?.emit("available", json)
            }

            socket?.on(Socket.EVENT_DISCONNECT) {
                _connectionState.value = SocketConnectionState.Disconnected
            }

            socket?.on(Socket.EVENT_CONNECT_ERROR) {
                _connectionState.value = SocketConnectionState.Error
            }

            socket?.connect()
        } catch (_: Exception) {
            _connectionState.value = SocketConnectionState.Error
        }
    }

    override fun disconnect() {
        socket?.disconnect()
        socket?.off()
        _connectionState.value = SocketConnectionState.Disconnected
    }


}