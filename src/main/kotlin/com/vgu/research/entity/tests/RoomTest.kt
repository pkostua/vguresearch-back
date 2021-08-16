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

    val myObjectCount: Int
        get() = this.items.filter { it.owner != null && it.owner?.id == member?.id && it.type != "alive" }.size
    val myAliveCount: Int
        get() = this.items.filter { it.owner != null && it.owner?.id == member?.id && it.type == "alive" }.size

    val collectiveObjectCount: Int
        get() = this.items.filter { it.owner == null && it.type != "alive" }.size
    val collectiveAliveCount: Int
        get() = this.items.filter { it.owner == null && it.type == "alive" }.size

    val otherObjectCount: Int
        get() = this.items.filter { it.owner != null && it.owner?.id != member?.id && it.type != "alive" }.size
    val otherAliveCount: Int
        get() = this.items.filter { it.owner != null && it.owner?.id != member?.id && it.type == "alive" }.size

    val mySquare: Int
        get() = this.items.filter {  it.owner != null && it.owner?.id == member?.id }.map { it.square }.sum()
    val collectiveSquare: Int
        get() = this.items.filter {  it.owner == null  }.map { it.square }.sum()
    val otherSquare: Int
        get() = this.items.filter {  it.owner != null && it.owner?.id != member?.id }.map { it.square }.sum()


}
