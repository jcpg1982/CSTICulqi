package com.csticorp.culqi.test.domain.usesCase

import com.csticorp.culqi.test.data.repository.Repository
import javax.inject.Inject

class AllUsersUseCase @Inject constructor(private val repository: Repository) {

    operator fun invoke(page: Int, perPage: Int) =
        repository.getAllUsers(page = page, perPage = perPage)
}