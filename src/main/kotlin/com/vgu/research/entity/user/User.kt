package com.vgu.research.entity.user

import com.fasterxml.jackson.annotation.JsonIgnore
import com.vgu.research.entity.tests.SppAdult
import com.vgu.research.entity.tests.SppChildren
import javax.persistence.*

@Entity
@Table(name="users")
class User{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long =0
    var firstName: String =""
    var lastName: String =""
    var familyPosition: FamilyPosition? = null
    var age: Int? = null
    var tmpUserId: String? = null
    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true, mappedBy = "user")
    var familyMembers: MutableList<FamilyMember> = mutableListOf()

    @JsonIgnore
    @OneToMany(cascade=[CascadeType.REMOVE], orphanRemoval = true, mappedBy = "user" )
    var sppChildrenTestList:MutableList<SppChildren> = mutableListOf()

    @JsonIgnore
    @OneToMany(cascade=[CascadeType.REMOVE], orphanRemoval = true, mappedBy = "user" )
    var sppAdultTestList:MutableList<SppAdult> = mutableListOf()

    @OneToOne(cascade=[CascadeType.ALL])
    var account: UserAccount? = null

    fun update(user: User): User{
        this.age=user.age
        this.familyPosition = user.familyPosition
        this.familyMembers.clear()
        this.familyMembers.filter { exist->user.familyMembers.find { exist.id == it.id } ==null }.forEach{
            this.familyMembers.remove(it)
        }
        this.familyMembers.addAll(user.familyMembers.filter { it.id == null })
        user.familyMembers.forEach {
            this.familyMembers.add(it.withUser(this))
        }

        return this
    }

}
