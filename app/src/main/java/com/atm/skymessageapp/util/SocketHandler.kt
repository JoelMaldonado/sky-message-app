package com.atm.skymessageapp.util

import io.socket.client.IO
import io.socket.client.Socket
import java.net.URISyntaxException

object SocketHandler {

    private lateinit var mSocket: Socket

    @Synchronized
    fun setSocket(serverUrl: String) {
        try {
            val options = IO.Options()
            options.reconnection = true
            mSocket = IO.socket(serverUrl, options)
        } catch (e: URISyntaxException) {
            e.printStackTrace()
        }
    }

    @Synchronized
    fun getSocket(): Socket {
        return mSocket
    }

    @Synchronized
    fun establishConnection() {
        mSocket.connect()
    }

    @Synchronized
    fun closeConnection() {
        mSocket.disconnect()
    }
}