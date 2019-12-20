package com.vgu.research.configurations.env

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties("spring.social.vk")
class VkontakteEnvironment: CredentialsProperties() {

}