package com.csticorp.culqi.test.domain.usesCase

import com.csticorp.culqi.test.data.repository.Repository
import com.csticorp.culqi.test.data.request.RegisterUserRequest
import javax.inject.Inject

class RegisterUserUseCase @Inject constructor(private val repository: Repository) {

    operator fun invoke(body: RegisterUserRequest) = repository.registerUser(body)
}