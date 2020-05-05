package com.vgu.research.sevices

import com.vgu.research.entity.user.OAuthProviderEnum
import com.vgu.research.entity.user.User
import com.vgu.research.entity.user.UserAccount
import com.vgu.research.repository.UserAccountRepository
import com.vgu.research.repository.UserRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.social.connect.Connection
import org.springframework.social.connect.ConnectionSignUp
import org.springframework.social.connect.UserProfile
import org.springframework.social.security.SocialUser
import org.springframework.social.security.SocialUserDetails
import org.springframework.social.security.SocialUserDetailsService
import org.springframework.stereotype.Service
import org.springframework.util.StringUtils.hasText

import java.util.*

@Service
class AuthUserDetailService(
        val userRepository: UserRepository,
        val userAccountRepository: UserAccountRepository
): SocialUserDetailsService, ConnectionSignUp {

    @Override
    override fun loadUserByUserId(accountId: String): SocialUserDetails {
        val user = userRepository.findByAccountId(accountId)?: throw UsernameNotFoundException(accountId)
        return SocialUser(
                user.account?.id,
                "",
                true,
                true,
                true,
                true,
                Collections.singletonList(SimpleGrantedAuthority("ROLE_USER"))
        );
    }

    @Override
    override fun execute(connection: Connection<*>): String? {
        val profile = connection.fetchUserProfile();
        return if(userRepository.findByAccountId(getProfileId(profile)) != null) null else registerUser(connection).account?.id
    }

    private fun registerUser(connection: Connection<*>): User {
        val profile = connection.fetchUserProfile()
        val userAccount = userAccountRepository.save(
                UserAccount(
                        getProfileId(profile),
                        profile.email?:"",
                        profile.username?:"",
                        profile.firstName?:"",
                        profile.lastName?:"",
                        connection.imageUrl?:"",
                        connection.profileUrl?:"",
                        connection.displayName?:"",
                        OAuthProviderEnum.valueOf(connection.key.providerId.toUpperCase())
                )

        );

        val user = User();
        user.firstName= profile.firstName
        user.lastName = profile.lastName
        user.account = userAccount
        return userRepository.save(user)
    }

    private fun getProfileId(profile: UserProfile): String {
        if (hasText(profile.id)) return profile.id;
        if (hasText(profile.email)) return profile.email;
        if (hasText(profile.username)) return profile.username
        throw IllegalArgumentException("can't fetch user ID")
    }
}
