package com.vgu.research.entity.user

import javax.persistence.*

@Entity
class User{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long =0
    var firstName: String =""
    var lastName: String =""
    var accountId: String=""

    @OneToOne
    var account: UserAccount = UserAccount()

}