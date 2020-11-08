package com.vgu.research.web

import com.vgu.research.entity.tests.RoomTest
import com.vgu.research.entity.tests.RoomTestItem
import com.vgu.research.repository.RoomTestRepository
import com.vgu.research.repository.UserRepository
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*


@RestController("/api/room")
@RequestMapping("/api/room")
class RoomTestController (val userRepository: UserRepository,
                          val roomTestRepository: RoomTestRepository
                          ) {
    @PostMapping
    @Transactional
    fun postTest(@RequestParam tmpUserId: String?,  @RequestParam memberId: Long, @RequestBody data: RoomTestItemsWrapper): RoomTest  {
        val accountId = SecurityContextHolder.getContext().authentication.name
        val user = when{
            accountId != "anonymousUser"-> userRepository.findByAccountId(accountId)?: throw Exception("User not found")
            tmpUserId != null  -> userRepository.findByTmpUserId(tmpUserId)?: throw Exception("User not found")
            else -> throw Exception("user not found")
        }
        val member = user.familyMembers.find { it.id == memberId }?: throw Exception("MemberNotFound")
        roomTestRepository.findAllByUserAndMember(user, member).also { if(it.isNotEmpty())roomTestRepository.deleteAll(it) }
        val test = RoomTest(user = user, member =  member)
        data.data.forEach{
            it.id = 0
            it.room = test
        }
        test.items = data.data
        return roomTestRepository.save(test)
    }

    @GetMapping("/data")
    fun getTest(@RequestParam tmpUserId: String?,  @RequestParam memberId: Long): MutableList<RoomTestItem>?  {
        val accountId = SecurityContextHolder.getContext().authentication.name
        val user = when{
            accountId != "anonymousUser"-> userRepository.findByAccountId(accountId)?: throw Exception("User not found")
            tmpUserId != null  -> userRepository.findByTmpUserId(tmpUserId)?: throw Exception("User not found")
            else -> throw Exception("user not found")
        }
        val member = user.familyMembers.find { it.id == memberId }?: throw Exception("MemberNotFound")
        return roomTestRepository.findAllByUserAndMember(user, member).firstOrNull()?.items
    }

    data class RoomTestItemsWrapper(
            val data: MutableList<RoomTestItem>
    )
}
