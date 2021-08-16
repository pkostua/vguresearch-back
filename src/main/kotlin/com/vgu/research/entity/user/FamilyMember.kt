package com.vgu.research.entity.user

import com.fasterxml.jackson.annotation.JsonIgnore
import com.vgu.research.entity.tests.*
import javax.persistence.*
import kotlin.jvm.Transient

@Entity
class FamilyMember (
        var name: String="",
        var age: Int = 0,
        var sex: Sex? = null,
        var familyPosition: FamilyPosition? = null,
        var birthOrder: Int? = null
)
{
    @JsonIgnore @ManyToOne
    var user: User? = null
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Transient var hasAnketa: Boolean? = null
    @Transient var hasRoom: Boolean? = null
    @Transient var hasSppChildren: Boolean? = null
    @Transient var hasSppAdult: Boolean? = null

    fun withUser(user: User?): FamilyMember{
        this.user = user
        return this
    }

    fun update(other: FamilyMember): FamilyMember{
        this.name= other.name
        this.age = other.age
        this.sex = other.sex
        this.familyPosition = other.familyPosition
        return this
    }

    @JsonIgnore
    @OneToMany(cascade=[CascadeType.REMOVE], orphanRemoval = true, mappedBy = "child" )
    var sppChildrenTestList:MutableList<SppChildren> = mutableListOf()

    @JsonIgnore
    @OneToMany(cascade=[CascadeType.REMOVE], orphanRemoval = true, mappedBy = "child" )
    var sppAdultTestList:MutableList<SppAdult> = mutableListOf()

    @JsonIgnore
    @OneToMany(cascade=[CascadeType.REMOVE], orphanRemoval = true, mappedBy = "member" )
    var roomTestList:MutableList<RoomTest> = mutableListOf()

    @JsonIgnore
    @OneToMany(cascade=[CascadeType.REMOVE], orphanRemoval = true, mappedBy = "child" )
    var anketaList:MutableList<Anketa> = mutableListOf()

    @JsonIgnore
    @OneToMany(cascade=[CascadeType.REMOVE], orphanRemoval = true, mappedBy = "owner" )
    var roomTestItemList:MutableList<RoomTestItem> = mutableListOf()
}
