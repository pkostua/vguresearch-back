package com.vgu.research.web

import com.vgu.research.entity.user.User
import com.vgu.research.repository.UserRepository
import org.springframework.security.core.context.SecurityContextHolder

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class IndexController (val userRepository: UserRepository) {
    @GetMapping("/")
    fun index(): User? {
        val accountId = SecurityContextHolder.getContext().authentication.name
        return userRepository.findByAccountId(accountId)?: throw RuntimeException("user not found, accountId=$accountId")
    }
}