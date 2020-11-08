package com.vgu.research.entity.tests

import com.fasterxml.jackson.annotation.JsonIgnore
import com.vgu.research.entity.user.FamilyMember
import javax.persistence.*


@Entity
class RoomTestItem() {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long =0

    @ManyToOne @JsonIgnore
    var room: RoomTest? = null

    var square: Int = 0

    @ManyToOne
    var owner: FamilyMember? = null

    var positionX: Int = 0
    var positionY: Int = 0

    var initX: Int = 0
    var initY: Int = 0

    var name: String = ""
    var img: String = ""
    var rotate: Int = 0
    var zedIndex: Int? = null



}
