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
    fun postTest(@RequestParam tmpUserId: String?, @RequestParam name: String?, @RequestParam age: Int?, @RequestBody ansList: TestDtoAnsWrapper): SppAdult  {
        val accountId = SecurityContextHolder.getContext().authentication.name
        var user =  userRepository.findByAccountId(accountId)
        if(user == null && tmpUserId != null){
            user = userRepository.findByTmpUserId(tmpUserId)
            if(user == null){
                user = User()
                user.tmpUserId = tmpUserId
                user.account = null
                userRepository.save(user)
            }
        }
        var familyMember = user?.familyMembers?.find { it.name == name && it.age == age }
        if(familyMember == null && name !== null && age !== null && user == null){
            familyMember = this.familyMemberRepository.save(FamilyMember(name,age,null, FamilyPosition.PARENT).withUser(user))
        }
        val test = SppAdult(ansList.data, user, familyMember)
        return this.sppAdultRepository.save(test)

    }

}
