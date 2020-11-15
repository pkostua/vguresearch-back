package com.vgu.research.web

import com.vgu.research.dto.FamilyMemberDto
import com.vgu.research.repository.FamilyMemberRepository
import com.vgu.research.repository.UserRepository
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*


@RestController("/api/admin")
@RequestMapping("/api/admin")
class AdminController (val familyMemberRepository: FamilyMemberRepository, val userRepository: UserRepository
                          ) {
    @GetMapping("/byMember")
    fun getTestData(@RequestParam tmpUserId: String?): List<FamilyMemberDto> {
        val accountId = SecurityContextHolder.getContext().authentication.name
        val user = when{
            accountId != "anonymousUser"-> userRepository.findByAccountId(accountId) ?: throw Exception("User not found")
            tmpUserId != null  -> userRepository.findByTmpUserId(tmpUserId) ?: throw Exception("User not found")
            else -> throw Exception("user not found")
        }
        if(!user.isAdmin) throw Exception("user is not admin")
        return familyMemberRepository.findAll().map { FamilyMemberDto(it) }
    }


}
