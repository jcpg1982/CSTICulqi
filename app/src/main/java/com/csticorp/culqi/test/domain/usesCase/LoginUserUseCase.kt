package com.csticorp.culqi.test.domain.usesCase

import com.csticorp.culqi.test.data.repository.Repository
import com.csticorp.culqi.test.data.request.LoginRequest
import javax.inject.Inject

class LoginUserUseCase @Inject constructor(private val repository: Repository) {

    operator fun invoke(body: LoginRequest) = repository.loginUser(body)
}