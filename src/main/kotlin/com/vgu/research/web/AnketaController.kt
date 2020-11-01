package com.vgu.research.web

import com.vgu.research.entity.tests.*
import com.vgu.research.repository.AnketaRepository
import com.vgu.research.repository.UserRepository
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*


@RestController("/api/anketa")
@RequestMapping("/api/anketa")
class AnketaController (val userRepository: UserRepository,
                        val anketaRepository: AnketaRepository
                          ) {
    @GetMapping
    fun getTestData(): List<AnketaQuestionTypeDto> {
        return AnketaQuestionType.values().map { AnketaQuestionTypeDto(it) }
    }

    @PostMapping
    @Transactional
    fun postTest(@RequestParam tmpUserId: String?, @RequestParam childId: Long, @RequestBody data: AnketaQuestionTypeDtoWrapper): Anketa {
        val accountId = SecurityContextHolder.getContext().authentication.name
        val user = when{
            accountId != "anonymousUser"-> userRepository.findByAccountId(accountId) ?: throw Exception("User not found")
            tmpUserId != null  -> userRepository.findByTmpUserId(tmpUserId) ?: throw Exception("User not found")
            else -> throw Exception("user not found")
        }
        anketaRepository.findAllByUserAndChildId(user, childId).also { if(it.isNotEmpty())anketaRepository.deleteAll(it)}
        val a = Anketa()
        a.user = user
        a.child = user.familyMembers.find { it.id == childId }
        a.ansList = data.data.map { AnketaQuestion(it.id, it.ans?:"", a) }.toMutableList()
        return anketaRepository.save(a)

    }

}
