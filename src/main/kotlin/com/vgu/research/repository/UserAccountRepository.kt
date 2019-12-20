package com.vgu.research.repository

import com.vgu.research.entity.user.UserAccount
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserAccountRepository : CrudRepository<UserAccount, Long>, JpaSpecificationExecutor<UserAccount> {

}