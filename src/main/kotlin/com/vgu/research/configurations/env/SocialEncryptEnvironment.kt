package com.vgu.research.configurations.env

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties("spring.social.encrypt")
class SocialEncryptEnvironment {
    val key: String? = null
    val salt: String? = null
}