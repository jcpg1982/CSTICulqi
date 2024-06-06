package com.csticorp.culqi.test.data.model

import com.csticorp.culqi.test.domain.utils.Constants
import com.csticorp.culqi.test.domain.utils.Constants.IntentsExtras.DATA_USER
import com.csticorp.culqi.test.domain.utils.Constants.IntentsExtras.EMAIL

sealed class Routes(open val route: String) {

    data object Init : Routes(Constants.Routes.INIT)

    class SignUpScreen(
        override var route: String = "${Constants.Routes.SIGN_UP}/{$EMAIL}"
    ) : Routes(route = route) {
        fun createNewRoute(email: String) = this.apply {
            route = "${Constants.Routes.SIGN_UP}/$email"
        }
    }

    class LoginScreen(
        override var route: String = "${Constants.Routes.LOGIN}/{$DATA_USER}"
    ) : Routes(route = route) {
        fun createNewRoute(dataUser: String) = this.apply {
            route = "${Constants.Routes.LOGIN}/$dataUser"
        }
    }

    data object Home : Routes(Constants.Routes.HOME)
}
