package com.vgu.research.entity.tests

import com.vgu.research.entity.user.FamilyMember
import com.vgu.research.entity.user.User
import java.util.*
import javax.persistence.*


@Entity
class RoomTest(
        @ManyToOne
        var user: User? = null,
        @OneToMany (cascade = [CascadeType.ALL], mappedBy = "room", orphanRemoval = true)
        var items: MutableList<RoomTestItem> = mutableListOf(),
        @ManyToOne
        var member: FamilyMember? = null
) {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long =0
    var date: Date = Date()

}
