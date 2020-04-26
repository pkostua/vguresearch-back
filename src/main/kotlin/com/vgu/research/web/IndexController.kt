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
        return userRepository.findByAccountId(accountId)?: throw RuntimeException("user not found, accountId=$accountId")
    }

    @PostMapping("/updateUser")
    fun updateUser(@RequestBody user: User)  {
        val accountId = SecurityContextHolder.getContext().authentication.name
        val existUser =  userRepository.findByAccountId(accountId)?: throw RuntimeException("user not found, accountId=$accountId")
        userRepository.save(existUser.update(user))
    }

}
