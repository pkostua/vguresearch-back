package com.vgu.research.entity.user

import javax.persistence.*


@Entity
data class UserAccount (
    @Id var id: String = "",
    var displayName: String = "",
    var firstName: String = "",
    var lastName: String = "",
    var email: String = "",
    var userName: String = "",

    var profileUrl: String? = null,

    var imageUrl: String? = null,

    @Enumerated(EnumType.STRING)
    var provider: OAuthProviderEnum = OAuthProviderEnum.VKONTAKTE
)