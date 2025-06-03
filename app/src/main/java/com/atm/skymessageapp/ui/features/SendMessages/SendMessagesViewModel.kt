package com.atm.skymessageapp.ui.features.SendMessages

import android.content.Context
import android.telephony.SmsManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.atm.skymessageapp.util.SocketHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import io.socket.client.Socket
import io.socket.emitter.Emitter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.json.JSONArray
import javax.inject.Inject

@HiltViewModel
class SendMessagesViewModel @Inject constructor(
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val _mensajes = MutableStateFlow<List<Pair<String, String>>>(emptyList())
    val mensajes: StateFlow<List<Pair<String, String>>> = _mensajes

    private lateinit var socket: Socket

    fun init() {
        conectarSocket()
    }

    private fun conectarSocket() {
        SocketHandler.setSocket("http://192.168.68.105:4343")
        SocketHandler.establishConnection()
        socket = SocketHandler.getSocket()
        socket.on("sms_batch", onSmsBatch)
    }

    private val onSmsBatch = Emitter.Listener { args ->
        val dataArray = args[0] as JSONArray
        val nuevosMensajes = mutableListOf<Pair<String, String>>()
        for (i in 0 until dataArray.length()) {
            val obj = dataArray.getJSONObject(i)
            val clientNumber = obj.getString("numero")
            val clientMessage = obj.getString("mensaje")
            nuevosMensajes.add(clientNumber to clientMessage)
            sendSms(phoneNumber = clientNumber, message = clientMessage)
        }

        viewModelScope.launch {
            _mensajes.value = nuevosMensajes
        }

    }

    override fun onCleared() {
        super.onCleared()
        socket.off("sms_batch", onSmsBatch)
        SocketHandler.closeConnection()
    }


    fun sendSms(phoneNumber: String, message: String): Result<Unit> {
        try {
            val smsManager = context.getSystemService(SmsManager::class.java)
            smsManager.sendTextMessage(phoneNumber, null, message, null, null)
            return Result.success(Unit)
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }

}