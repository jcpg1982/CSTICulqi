package com.csticorp.culqi.test.data.repository

import com.csticorp.culqi.test.data.request.LoginRequest
import com.csticorp.culqi.test.data.request.RegisterUserRequest
import com.csticorp.culqi.test.data.response.GetListUserResponse
import com.csticorp.culqi.test.data.response.LoginUserResponse
import com.csticorp.culqi.test.data.response.RegisterUserResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface DataSource {

    fun getAllUsers(page: Int, perPage: Int): Flow<Response<GetListUserResponse>>
    fun registerUser(body: RegisterUserRequest): Flow<Response<RegisterUserResponse>>
    fun loginUser(body: LoginRequest): Flow<Response<LoginUserResponse>>
}