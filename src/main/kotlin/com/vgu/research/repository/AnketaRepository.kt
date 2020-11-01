package com.vgu.research.repository

import com.vgu.research.entity.tests.Anketa
import com.vgu.research.entity.tests.SppAdult
import com.vgu.research.entity.tests.SppChildren
import com.vgu.research.entity.user.User
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface AnketaRepository : CrudRepository<Anketa, Long>, JpaSpecificationExecutor<Anketa> {
    fun findAllByUserAndChildId(user: User, childId: Long): List<Anketa>
    fun existsByUserAndChildId(user: User, childId: Long): Boolean
}
