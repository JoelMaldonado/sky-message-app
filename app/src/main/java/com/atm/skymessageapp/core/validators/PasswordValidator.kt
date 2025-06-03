package com.atm.skymessageapp.core.validators

class PasswordValidator {
    companion object {
        fun validate(password: String): String? {
            return when {
                password.isBlank() -> "La contraseña no puede estar vacía"
                password.length < 8 -> "La contraseña debe tener al menos 8 caracteres"
                else -> null
            }
        }
    }
}