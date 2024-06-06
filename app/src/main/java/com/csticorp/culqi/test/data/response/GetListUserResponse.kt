package com.csticorp.culqi.test.data.response

import com.google.gson.annotations.SerializedName

data class GetListUserResponse(
    @SerializedName("data")
    val result: List<DataUser>,
    val page: Int,
    @SerializedName("per_page")
    val perPage: Int,
    val support: Support,
    val total: Int,
    @SerializedName("total_pages")
    val totalPages: Int
)