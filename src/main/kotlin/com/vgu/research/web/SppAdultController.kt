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
    fun postTest(@RequestParam tmpUserId: String?, @RequestParam childId: Long, @RequestBody ansList: TestDtoAnsWrapper): SppAdult  {
        val accountId = SecurityContextHolder.getContext().authentication.name
        val user = when{
            accountId != "anonymousUser"-> userRepository.findByAccountId(accountId)?: throw Exception("User not found")
            tmpUserId != null  -> userRepository.findByTmpUserId(tmpUserId)?: throw Exception("User not found")
            else -> throw Exception("user not found")
        }
        sppAdultRepository.findAllByUserAndChildId(user, childId).also { if(it.isNotEmpty())sppAdultRepository.deleteAll(it) }
        val child = user.familyMembers.find { it.id == childId }?: throw Exception("Child not found")
        val test = SppAdult(ansList.data, user, null, child)
        return this.sppAdultRepository.save(test)

    }

    @GetMapping("/data")
    fun getTest(@RequestParam tmpUserId: String?,  @RequestParam childId: Long): String?  {
        val accountId = SecurityContextHolder.getContext().authentication.name
        val user = when{
            accountId != "anonymousUser"-> userRepository.findByAccountId(accountId)?: throw Exception("User not found")
            tmpUserId != null  -> userRepository.findByTmpUserId(tmpUserId)?: throw Exception("User not found")
            else -> throw Exception("user not found")
        }
        return sppAdultRepository.findAllByUserAndChildId(user, childId).firstOrNull()?.src
    }

}
