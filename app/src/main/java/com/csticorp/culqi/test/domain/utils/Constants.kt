package com.csticorp.culqi.test.domain.utils

object Constants {

    object Routes {
        const val INIT = "init"
        const val SIGN_UP = "signUp"
        const val LOGIN = "login"
        const val HOME = "home"
    }

    object IntentsExtras {
        const val EMAIL = "email"
        const val DATA_USER = "dataUser"
    }

    object TileRoutes {
        const val TITLE_HOME = "Inicio"
    }

    object Regex {
        val ONLY_LETTERS = Regex("^[a-zA-ZáéíóúñÑÁÉÍÓÚ@.,+\\s]*$")
        val ONLY_NUMBERS = Regex("^[0-9.,\\s]*$")
        val MIXTO = Regex("^[a-zA-Z0-9áéíóúñÑÁÉÍÓÚ@*.,+()/\\s]*$")
    }
}