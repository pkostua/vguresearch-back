package com.vgu.research.configurations

import com.vgu.research.configurations.env.FacebookEnvironment
import com.vgu.research.configurations.env.SocialEncryptEnvironment
import com.vgu.research.configurations.env.VkontakteEnvironment
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.encrypt.Encryptors.noOpText
import org.springframework.security.crypto.encrypt.Encryptors.text
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurer;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.security.AuthenticationNameUserIdSource;
import org.springframework.social.vkontakte.connect.VKontakteConnectionFactory;
import org.springframework.util.StringUtils.hasText
import javax.sql.DataSource


@EnableSocial
@Configuration
@RequiredArgsConstructor
class SocialConfiguration: SocialConfigurer {

    private val dataSource: DataSource? = null
    private val environment: Environment? = null
    private val fbEnv: FacebookEnvironment? = null
    private val vkEnv: VkontakteEnvironment? = null
    private val encryptEnv: SocialEncryptEnvironment? = null
    private val connectionSignUpService: ConnectionSignUp? = null


    override fun addConnectionFactories(connectionCfg: ConnectionFactoryConfigurer, env: Environment?) {
        connectionCfg.addConnectionFactory(FacebookConnectionFactory(fbEnv!!.clientId, fbEnv.clientSecret))
        connectionCfg.addConnectionFactory(VKontakteConnectionFactory(vkEnv!!.clientId, vkEnv.clientSecret))
    }


    override fun getUserIdSource(): UserIdSource? {
        return AuthenticationNameUserIdSource()
    }

    override fun getUsersConnectionRepository(locator: ConnectionFactoryLocator?): UsersConnectionRepository? {
        val encryptor = if (hasText(encryptEnv?.key)) text(encryptEnv!!.key, encryptEnv.salt) else noOpText()
        val connectionRepository = JdbcUsersConnectionRepository(dataSource, locator, encryptor)
        connectionRepository.setConnectionSignUp(connectionSignUpService)
        return connectionRepository
    }

    @Bean
    fun providerSignInUtils(locator: ConnectionFactoryLocator?, repository: UsersConnectionRepository?): ProviderSignInUtils? {
        return ProviderSignInUtils(locator, repository)
    }


}