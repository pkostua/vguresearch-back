package com.vgu.research.repository

import com.vgu.research.entity.tests.SppChildren
import com.vgu.research.entity.user.User
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface SppChildrenRepository : CrudRepository<SppChildren, Long>, JpaSpecificationExecutor<SppChildren> {
    fun findAllByUserAndChildId(user: User, childId: Long): List<SppChildren>
    fun existsByUserAndChildId(user: User, childId: Long): Boolean
}
