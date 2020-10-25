package com.vgu.research.web

import com.vgu.research.entity.user.User
import com.vgu.research.repository.UserRepository
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/user")
class IndexController (val userRepository: UserRepository) {
    @GetMapping("/getUser")
    fun index(): User {
        val accountId = SecurityContextHolder.getContext().authentication.name
        return userRepository.findByAccountId(accountId) ?: throw RuntimeException("user not found, accountId=$accountId")
    }

    @GetMapping("/getTmpUser")
    fun getByTmpUser(@RequestParam tmpUserId: String): User? {
        return userRepository.findByTmpUserId(tmpUserId)
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
