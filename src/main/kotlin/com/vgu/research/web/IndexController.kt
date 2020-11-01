package com.vgu.research.web

import com.vgu.research.entity.user.CHILDREN_POSITIONS
import com.vgu.research.entity.user.User
import com.vgu.research.repository.RoomTestRepository
import com.vgu.research.repository.SppAdultRepository
import com.vgu.research.repository.SppChildrenRepository
import com.vgu.research.repository.UserRepository
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/user")
class IndexController (val userRepository: UserRepository,
                        val roomTestRepository: RoomTestRepository,
                       val anketaRepository: RoomTestRepository,
                       val sppChildrenRepository: SppChildrenRepository,
                       val sppAdultRepository: SppAdultRepository
) {
    @GetMapping("/getUser")
    fun index(): User {
        val accountId = SecurityContextHolder.getContext().authentication.name
        val user = userRepository.findByAccountId(accountId) ?: throw RuntimeException("user not found, accountId=$accountId")
        fillTests(user)
        return user
    }

    @GetMapping("/getTmpUser")
    fun getByTmpUser(@RequestParam tmpUserId: String): User? {
        val user =  userRepository.findByTmpUserId(tmpUserId)
        user?.let { fillTests(it) }
        return user
    }

    private fun fillTests(user: User): User{
        user.familyMembers.filter {it.familyPosition in CHILDREN_POSITIONS}.forEach{child->
            child.id?.let {
                child.hasAnketa = anketaRepository.existsByUserAndMemberId(user, it)
                child.hasRoom = roomTestRepository.existsByUserAndMemberId(user, it)
                child.hasSppChildren = sppChildrenRepository.existsByUserAndChildId(user, it)
                child.sppAdultList = sppAdultRepository.findAllByUserAndChildId(user,it).mapNotNull { it.parent?.id }.toMutableList()
            }
        }
        return user
    }

    @PostMapping("/updateUser")
    fun updateUser(@RequestParam(required = false) tmpUserId: String?, @RequestBody user: User)  {
        val accountId = SecurityContextHolder.getContext().authentication.name
        val existUser = when {
            accountId != "anonymousUser" -> userRepository.findByAccountId(accountId) ?: throw RuntimeException("user not found, accountId=$accountId")
            tmpUserId != null -> {
                var tmpUser = userRepository.findByTmpUserId(tmpUserId)
                if(tmpUser == null){
                    tmpUser = User()
                    tmpUser.tmpUserId = tmpUserId
                }
                tmpUser
            }
            else -> throw RuntimeException("user not found")
        }
        userRepository.save(existUser.update(user))
    }

}
