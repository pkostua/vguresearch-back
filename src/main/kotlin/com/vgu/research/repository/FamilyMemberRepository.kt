package com.vgu.research.repository

import com.vgu.research.entity.user.FamilyMember
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface FamilyMemberRepository : CrudRepository<FamilyMember, Long>, JpaSpecificationExecutor<FamilyMember> {

}
