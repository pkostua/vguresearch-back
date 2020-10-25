package com.vgu.research.configurations

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import org.springframework.social.security.SpringSocialConfigurer


@Configuration
@EnableWebSecurity
class WebSecurity : WebSecurityConfigurerAdapter() {

    @Value("\${server.servlet.session.cookie.name")
    private val cookieSessionName: String? = null


    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http
                .logout()
                .logoutRequestMatcher(AntPathRequestMatcher("/signout"))
                .deleteCookies(cookieSessionName)
                .and()
                .authorizeRequests()
                .antMatchers("/api/user/getUser").authenticated()
                .anyRequest().permitAll()
                .and()
                .apply(SpringSocialConfigurer())
                .and().csrf().disable()
    }


}
