package com.csticorp.culqi.test.data.repository

import com.csticorp.culqi.test.BuildConfig
import com.csticorp.culqi.test.data.request.LoginRequest
import com.csticorp.culqi.test.data.request.RegisterUserRequest
import com.csticorp.culqi.test.data.response.GetListUserResponse
import com.csticorp.culqi.test.data.response.LoginUserResponse
import com.csticorp.culqi.test.data.response.RegisterUserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface WebService {

    @GET(BuildConfig.ALL_USERS)
    suspend fun getAllUsers(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Response<GetListUserResponse>

    @POST(BuildConfig.REGISTER)
    suspend fun registerUser(@Body body: RegisterUserRequest): Response<RegisterUserResponse>

    @POST(BuildConfig.LOGIN)
    suspend fun loginUser(@Body body: LoginRequest): Response<LoginUserResponse>
}