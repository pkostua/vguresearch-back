package com.vgu.research.repository

import com.vgu.research.entity.tests.Anketa
import com.vgu.research.entity.tests.SppChildren
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface AnketaRepository : CrudRepository<Anketa, Long>, JpaSpecificationExecutor<SppChildren> {

}
