package com.vgu.research.entity.user

import com.fasterxml.jackson.annotation.JsonIgnore
import com.vgu.research.entity.tests.SppChildren
import javax.persistence.*

@Entity
class FamilyMember (
        var name: String="",
        var age: Int = 0,
        var sex: Sex? = null,
        var familyPosition: FamilyPosition? = null
)
{
    @JsonIgnore @ManyToOne
    var user: User? = null
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? =0

    fun withUser(user: User?): FamilyMember{
        this.user = user
        return this
    }

    @JsonIgnore
    @OneToMany(cascade=[CascadeType.REMOVE], orphanRemoval = true, mappedBy = "familyMember" )
    var sppTestList:MutableList<SppChildren> = mutableListOf()
}
