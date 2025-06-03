package com.atm.skymessageapp.core.validators

class EmailValidator {
    companion object {
        fun validate(email: String): String? {
            return when {
                email.isBlank() -> "El correo no puede estar vacío"
                email.length < 10 -> "El correo debe tener al menos 10 caracteres"
                !android.util.Patterns.EMAIL_ADDRESS.matcher(email)
                    .matches() -> "El correo no es válido"

                else -> null
            }
        }
    }
}