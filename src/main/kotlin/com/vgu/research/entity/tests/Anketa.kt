package com.vgu.research.entity.tests

import com.vgu.research.dto.SppBallType
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

    var ball: Double? = 0.0

    val smv: Double
        get() = ansList.filter { it.type.type == SppBallType.smv}.sumByDouble {
            val index = it.type.ansList.indexOf(it.ans)
            if(index == -1) 0.0
            else it.type.ballList[index]
        }

    val sft: Double
        get() = ansList.filter { it.type.type == SppBallType.sft}.sumByDouble {
            val index = it.type.ansList.indexOf(it.ans)
            if(index == -1) 0.0
            else it.type.ballList[index]
        }

    val st: Double
        get() = ansList.filter { it.type.type == SppBallType.st}.sumByDouble {
            val index = it.type.ansList.indexOf(it.ans)
            if(index == -1) 0.0
            else it.type.ballList[index]
        }

    val ss: Double
        get() = ansList.filter { it.type.type == SppBallType.ss}.sumByDouble {
            val index = it.type.ansList.indexOf(it.ans)
            if(index == -1) 0.0
            else it.type.ballList[index]
        }

    val sp: Double
        get() = ansList.filter { it.type.type == SppBallType.sp}.sumByDouble {
            val index = it.type.ansList.indexOf(it.ans)
            if(index == -1) 0.0
            else it.type.ballList[index]
        }

    val sts: Double
        get() = ansList.filter { it.type.type == SppBallType.sts}.sumByDouble {
            val index = it.type.ansList.indexOf(it.ans)
            if(index == -1) 0.0
            else it.type.ballList[index]
        }

}
