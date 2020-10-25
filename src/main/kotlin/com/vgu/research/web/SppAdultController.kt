package com.vgu.research.web

import com.vgu.research.dto.TestDto
import com.vgu.research.dto.TestDtoAnsWrapper
import com.vgu.research.entity.tests.SppAdult
import com.vgu.research.entity.user.FamilyMember
import com.vgu.research.entity.user.FamilyPosition
import com.vgu.research.entity.user.Sex
import com.vgu.research.entity.user.User
import com.vgu.research.repository.FamilyMemberRepository
import com.vgu.research.repository.SppAdultRepository
import com.vgu.research.repository.UserRepository
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*


@RestController("/api/sppAdult")
@RequestMapping("/api/sppAdult")
class SppAdultController (val userRepository: UserRepository,
                          val sppAdultRepository: SppAdultRepository,
                          val familyMemberRepository: FamilyMemberRepository
                          ) {
    @GetMapping
    fun getTestData(): List<TestDto> {
        return SppAdult.data
    }

    @PostMapping
    @Transactional
    fun postTest(@RequestParam tmpUserId: String?, @RequestParam parenId: Long?, @RequestParam childId: Long?, @RequestBody ansList: TestDtoAnsWrapper): SppAdult  {
        val accountId = SecurityContextHolder.getContext().authentication.name
        val user = when{
            accountId != "anonymousUser"-> userRepository.findByAccountId(accountId)
            tmpUserId != null  -> userRepository.findByTmpUserId(tmpUserId)
            else -> throw Exception("user not found")
        }
        val parent = user?.familyMembers?.find { it.id == parenId }
        val child = user?.familyMembers?.find { it.id == childId }
        val test = SppAdult(ansList.data, user, parent, child)
        return this.sppAdultRepository.save(test)

    }

}
