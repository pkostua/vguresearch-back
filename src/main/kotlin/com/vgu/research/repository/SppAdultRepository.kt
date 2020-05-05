package com.vgu.research.repository

import com.vgu.research.entity.tests.SppAdult
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface SppAdultRepository : CrudRepository<SppAdult, Long>, JpaSpecificationExecutor<SppAdult> {

}
