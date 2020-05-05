package com.vgu.research.web

import com.vgu.research.dto.TestDto
import com.vgu.research.dto.TestDtoAnsWrapper
import com.vgu.research.entity.tests.SppAdult
import com.vgu.research.repository.SppAdultRepository
import com.vgu.research.repository.UserRepository
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*


@RestController("/api/sppAdult")
@RequestMapping("/api/sppAdult")
class SppAdultController (val userRepository: UserRepository,
                          val sppAdultRepository: SppAdultRepository
                          ) {
    @GetMapping
    fun getTestData(): List<TestDto> {
        return SppAdult.data
    }

    @PostMapping
    fun postTest(@RequestBody ansList: TestDtoAnsWrapper): SppAdult  {
        val accountId = SecurityContextHolder.getContext().authentication.name
        val user =  userRepository.findByAccountId(accountId)
        val test = SppAdult(ansList.data, user)
        return this.sppAdultRepository.save(test)

    }

}
