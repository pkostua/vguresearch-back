package com.vgu.research.repository

import com.vgu.research.entity.tests.RoomTest
import com.vgu.research.entity.user.FamilyMember
import com.vgu.research.entity.user.User
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface RoomTestRepository : CrudRepository<RoomTest, Long>, JpaSpecificationExecutor<RoomTest> {

    fun findAllByUserAndMember(user: User, member: FamilyMember): List<RoomTest>
    fun existsByUserAndMemberId(user: User, memberId: Long): Boolean
}
