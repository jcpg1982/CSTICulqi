package com.csticorp.culqi.test.data.repository

import com.csticorp.culqi.test.data.request.LoginRequest
import com.csticorp.culqi.test.data.request.RegisterUserRequest
import com.csticorp.culqi.test.data.response.GetListUserResponse
import com.csticorp.culqi.test.data.response.LoginUserResponse
import com.csticorp.culqi.test.data.response.RegisterUserResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(private val webService: WebService) : DataSource {

    override fun getAllUsers(page: Int, perPage: Int): Flow<Response<GetListUserResponse>> = flow {
        val response = webService.getAllUsers(page = page, perPage = perPage)
        emit(response)
    }

    override fun registerUser(body: RegisterUserRequest): Flow<Response<RegisterUserResponse>> =
        flow {
            val response = webService.registerUser(body)
            emit(response)
        }

    override fun loginUser(body: LoginRequest): Flow<Response<LoginUserResponse>> = flow {
        val response = webService.loginUser(body)
        emit(response)
    }
}