package com.vgu.research.repository

import com.vgu.research.entity.user.User
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : CrudRepository<User, Long>, JpaSpecificationExecutor<User> {
    fun findByAccountId(accountId: String): User?
}