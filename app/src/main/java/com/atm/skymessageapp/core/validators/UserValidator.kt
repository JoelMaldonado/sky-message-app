package com.atm.skymessageapp.core.validators

class UserValidator {
    companion object {
        fun validate(user: String): String? {
            return when {
                user.isBlank() -> "El usuario no puede estar vacío"
                user.length < 10 -> "El correo debe tener al menos 10 caracteres"
                else -> null
            }
        }
    }
}