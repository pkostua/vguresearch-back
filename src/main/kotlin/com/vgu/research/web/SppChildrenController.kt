package com.vgu.research.web

import com.vgu.research.dto.TestDto
import com.vgu.research.dto.TestDtoAnsWrapper
import com.vgu.research.entity.tests.SppChildren
import com.vgu.research.entity.user.FamilyMember
import com.vgu.research.entity.user.FamilyPosition
import com.vgu.research.entity.user.Sex
import com.vgu.research.entity.user.User
import com.vgu.research.repository.FamilyMemberRepository
import com.vgu.research.repository.SppChildrenRepository
import com.vgu.research.repository.UserRepository
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*


@RestController("/api/sppChildren")
@RequestMapping("/api/sppChildren")
class SppChildrenController (val userRepository: UserRepository,
                             val sppChildrenRepository: SppChildrenRepository,
                                val familyMemberRepository: FamilyMemberRepository) {
    @GetMapping
    fun getTestData(): List<TestDto> {
        return SppChildren.data
    }

    @PostMapping
    fun updateUser(@RequestParam tmpUserId: String?, @RequestParam sex: Sex?, @RequestParam name: String?, @RequestParam age: Int?, @RequestBody ansList: TestDtoAnsWrapper): SppChildren  {
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
        if(familyMember == null && name !== null && age !== null){
            familyMember = this.familyMemberRepository.save(FamilyMember(name,age,sex?:Sex.BOY, FamilyPosition.CHILD).withUser(user))
        }
        val test = SppChildren(ansList.data, user, familyMember)
        return this.sppChildrenRepository.save(test)

    }

}
