package com.vgu.research.entity.tests

import com.fasterxml.jackson.annotation.JsonIgnore
import com.vgu.research.dto.SppChildrenBallType
import com.vgu.research.dto.TestDto
import com.vgu.research.dto.TestDtoAns
import com.vgu.research.entity.user.FamilyMember
import com.vgu.research.entity.user.User
import java.util.*
import javax.persistence.*


@Entity
class SppChildren() {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long =0
    var date: Date = Date()
    var sft: Int = 0 // суверенность физического тела
    var st: Int =0 //Суверенность территории
    var smv: Int =0 //Суверенность мира вещщей

    @ManyToOne
    var user: User? = null

    @ManyToOne
    var familyMember: FamilyMember? = null

    constructor(ansList: MutableList<TestDtoAns>, user: User?, familyMember: FamilyMember?):this(){
        this.user = user
        this.familyMember = familyMember
        ansList.forEach{ans->
            data.find { it.id == ans.id }?.let {
                when(it.type){
                    SppChildrenBallType.smv-> this.smv += it.bal[it.ansList.indexOf(ans.ans)]?:0
                    SppChildrenBallType.st-> this.st += it.bal[it.ansList.indexOf(ans.ans)]?:0
                    SppChildrenBallType.sft-> this.sft += it.bal[it.ansList.indexOf(ans.ans)]?:0
                }
            }
        }
    }


    companion object{
        public val data: MutableList<TestDto>
        get()= mutableListOf(
                TestDto(0,"Часто мне приходилось терпеть, когда родственники тискали или целовали меня.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppChildrenBallType.sft),
                TestDto(1,"Я обижался, когда меня в порядке наказания шлёпали или давали подзатыльники.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppChildrenBallType.sft),
                TestDto(3,"Я раздражался, если во время стирки мама вытряхивала мои вещи из карманов.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppChildrenBallType.smv),
                TestDto(4,"Меня никогда не заставляли есть насильно, как других детей.", mutableListOf("ДА", "НЕТ"), mutableListOf(1,-1), SppChildrenBallType.sft),
                TestDto(5,"Я часто огорчался, когда родители наводили порядок в моих игрушках.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppChildrenBallType.smv),
                TestDto(6,"Для меня было неприятным переживанием, когда мне стригли ногти.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppChildrenBallType.sft),
                TestDto(7,"У меня всегда было место (стол, сундучок, коробка), где я мог спрятать дорогие мне предметы.", mutableListOf("ДА", "НЕТ"), mutableListOf(1,-1), SppChildrenBallType.st),
                TestDto(8,"Нередко бывало, когда нужные мне вещи покупались как поощрение за хорошую учёбу или поведение.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppChildrenBallType.smv),
                TestDto(9,"Мне не нравилось, если без разрешения брали мою чашку или расческу.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppChildrenBallType.smv),
                TestDto(10,"Когда я чувствовал себя обиженным, я имел привычку запираться в ванной или туалете.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppChildrenBallType.st),
                TestDto(11,"Случалось, я обижался, когда взрослые начинали серьезный разговор и выставляли меня в другую комнату.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppChildrenBallType.st),
                TestDto(12,"Мне не нравилась семейная традиция донашивать хорошие вещи, доставшиеся от других детей.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppChildrenBallType.smv),
                TestDto(13,"Часто случалось, что, стесняясь попроситься в туалет, я долго терпел.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppChildrenBallType.sft),
                TestDto(14,"Я вполне ощутил, что отдать любимую игрушку – одно из самых сильных страданий маленького ребенка.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppChildrenBallType.smv),
                TestDto(15,"Взрослые почему-то считали, что могут войти в ванную или туалет, когда там ребенок, и не разрешали закрывать дверь на замок.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppChildrenBallType.st),
                TestDto(16,"Когда была приготовлена вкусная еда, то близкие мне взрослые считали необходимым накормить меня ею, даже преодолевая мое сопротивление.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppChildrenBallType.sft),
                TestDto(17,"Мне бы очень хотелось, чтобы на дверях детских комнат были замки, хотя взрослых это обижает.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppChildrenBallType.st),
                TestDto(18,"Я переживал оттого, что взрослые ошибочно думали, будто любую вещь можно заменить другой.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppChildrenBallType.smv),
                TestDto(19,"Я предпочитаю ходить в гости, а не звать гостей к себе.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppChildrenBallType.st),
                TestDto(20,"Мне не нравилось, когда взрослые любили меня пощекотать или ущипнуть.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppChildrenBallType.sft),
                TestDto(21,"Мне не нравилось, что новых вещей не покупали, пока у нас были старые, но в хорошем состоянии.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppChildrenBallType.smv),
                TestDto(22,"Как и многие другие дети, я мечтал построить домик из диванных подушек, но мне это неудавалось.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppChildrenBallType.st),
                TestDto(23,"Если я возвращался из магазина, то мог часть сдачи оставить себе.", mutableListOf("ДА", "НЕТ"), mutableListOf(1,-1), SppChildrenBallType.smv),
                TestDto(24,"Мне часто бывало неприятно, когда взрослые дотрагивались до меня.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppChildrenBallType.sft),
                TestDto(25,"Меня раздражало, если в моей комнате наводили порядок.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppChildrenBallType.st),
                TestDto(26,"Посещая врача, я боялся не боли, а того, что чужой человек будет меня трогать.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppChildrenBallType.sft),
                TestDto(27,"Наверное, я собственник: я постоянно раздражался оттого, что кто-то пользовался моими вещами.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppChildrenBallType.smv),
                TestDto(28,"Мне не нравилось, что взрослые ходят через ту комнату, где я играю с друзьями.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppChildrenBallType.st),
                TestDto(29,"Меня раздражало, когда приходилось в детстве носить вещи старшей сестры или брата.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppChildrenBallType.smv),
                TestDto(30,"Маленьких детей везде ждут огорчения: даже надевая шапку или завязывая шарф, взрослые умудряются зацепить за ухо или вырвать волосы.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppChildrenBallType.sft),
                TestDto(31,"У меня вызывало брезгливость, если мама или бабушка настаивали, чтобы я попробовал приготовленную ими еду “поварской” ложкой, которой они пробовали ее сами.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppChildrenBallType.smv),
                TestDto(32,"Я не любил, если у нас оставались на ночь гости, и мне приходилось менять спальное место.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppChildrenBallType.st),
                TestDto(33,"У меня нередко вызывало огорчение, когда мне надевали одежду через голову.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppChildrenBallType.sft),
                TestDto(34,"В нашей семье считалось важным тратить деньги не только на самое необходимое, но и на то, чего очень хочется ребенку (дорогие книги и приборы – бинокль, словарь, – без которых можно было вполне обойтись).", mutableListOf("ДА", "НЕТ"), mutableListOf(1,-1), SppChildrenBallType.smv)






        )
    }

}
