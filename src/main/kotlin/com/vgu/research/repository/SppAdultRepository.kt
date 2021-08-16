package com.vgu.research.repository

import com.vgu.research.entity.tests.SppAdult
import com.vgu.research.entity.user.User
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface SppAdultRepository : CrudRepository<SppAdult, Long>, JpaSpecificationExecutor<SppAdult> {

    fun findAllByUserAndChildId(user: User, childId: Long): List<SppAdult>
    fun existsByUserAndChildId(user: User, childId: Long): Boolean

}
