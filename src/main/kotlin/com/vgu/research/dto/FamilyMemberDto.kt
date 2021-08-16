package com.vgu.research.dto

import com.vgu.research.entity.tests.*
import com.vgu.research.entity.user.FamilyMember
import com.vgu.research.entity.user.FamilyPosition
import com.vgu.research.entity.user.Sex
import com.vgu.research.entity.user.User


data class FamilyMemberDto (
        var name: String,
        var age: Int,
        var sex: Sex?,
        var familyPosition: FamilyPosition?,
        var user: User?,
        var sppChildrenTestList:MutableList<SppChildren>,
        var sppAdultTestList:MutableList<SppAdult> = mutableListOf(),
        var roomTestList:MutableList<RoomTest> = mutableListOf(),
        var anketaList:MutableList<Anketa> = mutableListOf()
)
{
    constructor(m: FamilyMember): this(m.name, m.age, m.sex, m.familyPosition, m.user,
            m.sppChildrenTestList,  m.sppAdultTestList, m.roomTestList, m.anketaList)

}
