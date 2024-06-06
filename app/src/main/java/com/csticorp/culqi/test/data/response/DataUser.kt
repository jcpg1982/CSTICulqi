package com.csticorp.culqi.test.data.response

import com.google.gson.annotations.SerializedName

data class DataUser(
    val avatar: String = "",
    val email: String = "",
    @SerializedName("first_name")
    val firstName: String = "",
    val id: Int = -1,
    @SerializedName("last_name")
    val lastName: String = "",
    val selected: Boolean = false,
    val password: String = ""
) {
    val fullName get() = "$firstName $lastName"
    val letter: String
        get() {
            val split = fullName.split(" ")
            return if (split.size > 1) {
                "${split[0].substring(0, 1)}${split[1].substring(0, 1)}"
            } else {
                split[0].substring(0, 1)
            }
        }
}