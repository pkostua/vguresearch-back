package com.vgu.research.entity.tests

import com.vgu.research.entity.user.FamilyMember
import com.vgu.research.entity.user.User
import java.util.*
import javax.persistence.*


@Entity
class Anketa() {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long =0
    var date: Date = Date()

    @ManyToOne
    var user: User? = null

    @OneToMany(cascade = [CascadeType.ALL], mappedBy = "anketa")
    var ansList: MutableList<AnketaQuestion> = mutableListOf()

    @ManyToOne
    var child: FamilyMember? = null

}
