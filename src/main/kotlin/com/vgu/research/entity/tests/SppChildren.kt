package com.vgu.research.entity.tests

import com.fasterxml.jackson.databind.ObjectMapper
import com.vgu.research.dto.SppBallType
import com.vgu.research.dto.TestDto
import com.vgu.research.dto.TestDtoAns
import com.vgu.research.dto.TestDtoAnsWrapper
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
    var sp: Int = 0 //Суверенность привычек

    @ManyToOne
    var user: User? = null

    @ManyToOne
    var child: FamilyMember? = null

    @Column(columnDefinition = "text")
    var src: String? = null

    constructor(ansList: MutableList<TestDtoAns>, user: User?, child: FamilyMember?):this(){
        this.user = user
        this.child = child
        this.src = ObjectMapper().writeValueAsString(TestDtoAnsWrapper(ansList))
        ansList.forEach{ans->
            if(ans.ans != null) {
                data.find { it.id == ans.id }?.let {
                    when (it.type) {
                        SppBallType.smv -> ans.ans?.let { a -> this.smv += it.bal[it.ansList.indexOf(a)] ?: 0 }
                        SppBallType.st -> ans.ans?.let { a -> this.st += it.bal[it.ansList.indexOf(a)] ?: 0 }
                        SppBallType.sft -> ans.ans?.let { a -> this.sft += it.bal[it.ansList.indexOf(a)] ?: 0 }
                        SppBallType.sp -> ans.ans?.let { a -> this.sp += it.bal[it.ansList.indexOf(a)] ?: 0 }
                        else -> {
                        }
                    }
                }
            }
        }
    }


    companion object{
        public val data: MutableList<TestDto>
        get()= mutableListOf(
                TestDto(0,"У нас было в порядке вещей, если родители переключали телевизор на свой канал, когда ребенок его смотрел.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppBallType.sp),
                TestDto(1,"Часто ребенку приходилось терпеть, когда родственники тискали или целовали его.", mutableListOf("ДА", "НЕТ или такого не было"), mutableListOf(-1,1), SppBallType.sft),
                TestDto(2,"Ребенок обижался, когда его в порядке наказания шлёпали или давали подзатыльники.", mutableListOf("ДА", "НЕТ или такого не было"), mutableListOf(-1,1), SppBallType.sft),
                TestDto(3,"Ребенок раздражался, если во время стирки мама вытряхивала его вещи из карманов.", mutableListOf("ДА", "НЕТ или такого не было"), mutableListOf(-1,1), SppBallType.smv),
                TestDto(4,"Ребенка никогда не заставляли есть насильно, как других детей.", mutableListOf("ДА", "НЕТ"), mutableListOf(1,-1), SppBallType.sft),
                TestDto(5,"Ребенок часто огорчался, когда родители наводили порядок в его игрушках.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppBallType.smv),
                TestDto(6,"Для ребенка было неприятным переживанием, когда ему стригли ногти.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppBallType.sft),
                TestDto(7,"У ребенка всегда было место (стол, сундучок, коробка), где он мог спрятать дорогие ему предметы.", mutableListOf("ДА", "НЕТ"), mutableListOf(1,-1), SppBallType.st),
                TestDto(8,"Помню, что ребенок сильно грустил оттого, что ему не разрешали ложиться спать чуть позже, чем это было принято.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppBallType.sp),
                TestDto(9,"Нередко бывало, что нужные ребенку вещи покупались как поощрение за хорошую учёбу или поведение.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppBallType.smv),
                TestDto(10,"Ребенку не нравилось, если без разрешения брали его чашку или расческу.", mutableListOf("ДА", "НЕТ или такого не было"), mutableListOf(-1,1), SppBallType.smv),
                TestDto(11,"Когда ребенок чувствовал себя обиженным, он имел привычку запираться в ванной или туалете.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppBallType.st),
                TestDto(12,"Ребенок расстраивался, когда не мог доиграть из-за того, что взрослые звали его к себе.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppBallType.sp),
                TestDto(13,"Случалось, ребенок обижался, когда взрослые начинали серьезный разговор и выставляли его в другую комнату.", mutableListOf("ДА", "НЕТ или такого не было"), mutableListOf(-1,1), SppBallType.st),
                TestDto(14,"Даже если подходило время ложиться спать, ребенку обычно разрешали досмотреть любимую передачу.", mutableListOf("ДА", "НЕТ"), mutableListOf(1,-1), SppBallType.sp),
                TestDto(15,"Ребенку не нравилась семейная традиция донашивать хорошие вещи, доставшиеся от других детей.", mutableListOf("ДА", "НЕТ или такого не было"), mutableListOf(-1,1), SppBallType.smv),
                TestDto(16,"Часто случалось, что, стесняясь попроситься в туалет, ребенок долго терпел.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppBallType.sft),
                TestDto(17,"Ребенок вполне ощутил, что отдать любимую игрушку – одно из самых сильных страданий маленького ребенка.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppBallType.smv),
                TestDto(18,"Взрослые почему-то считали, что могут войти в ванную или туалет, когда там ребенок, и не разрешали закрывать дверь на замок.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppBallType.st),
                TestDto(19,"Вы старались делать с ребенком уроки, когда он уже справлялся сам.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppBallType.sp),
                TestDto(20,"Когда была приготовлена вкусная еда, то близкие ребенку взрослые считали необходимым накормить его ею, даже преодолевая его сопротивление.", mutableListOf("ДА", "НЕТ или такого не было"), mutableListOf(-1,1), SppBallType.sft),
                TestDto(21,"Ребенку бы очень хотелось, чтобы на дверях детских комнат были замки, хотя взрослых это обижает.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppBallType.st),
                TestDto(22,"Ребенку случалось огорчаться, если родители неожиданно брали его в гости.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppBallType.sp),
                TestDto(23,"Ребенок переживал оттого, что взрослые ошибочно думали, будто любую вещь можно заменить другой.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppBallType.smv),
                TestDto(24,"Ребенок предпочитает ходить в гости, а не звать гостей к себе.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppBallType.st),
                TestDto(25,"Ребенка раздражало, если взрослые не информировали его о своих планах.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppBallType.sp),
            TestDto(26,"Ребенку не нравилось, когда взрослые любили его пощекотать или ущипнуть.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppBallType.sft),
                TestDto(27,"Ребенку не нравилось, что новых вещей не покупали, пока у него были старые, но в хорошем состоянии.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppBallType.smv),
                TestDto(28,"Как и многие другие дети, ребенок мечтал построить домик из диванных подушек, но ему этого не разрешали.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppBallType.st),
            TestDto(29,"Если у ребенка с друзьями возникали общие планы, мы нередко старались их изменить.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppBallType.sp),
                TestDto(30,"Если ребенок возвращался из магазина, то мог часть сдачи оставить себе.", mutableListOf("ДА", "НЕТ"), mutableListOf(1,-1), SppBallType.smv),
            TestDto(31,"У нас было принято, чтобы родители всегда знали распорядок дня ребенка.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppBallType.sp),
                TestDto(32,"Ребенку часто бывало неприятно, когда взрослые дотрагивались до него.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppBallType.sft),
                TestDto(33,"Ребенка раздражало, если в его комнате наводили порядок.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppBallType.st),
            TestDto(34,"Во время обеда, если суп был горячим, ребенок мог сначала съесть второе, и мы это не запрещали.", mutableListOf("ДА", "НЕТ"), mutableListOf(1,-1), SppBallType.sp),
                TestDto(35,"Посещая врача, ребенок боялся не боли, а того, что чужой человек будет его трогать.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppBallType.sft),
                TestDto(36,"Наверное, мой ребенок собственник: он постоянно раздражался оттого, что кто-то пользовался его вещами.", mutableListOf("ДА", "НЕТ или такого не было"), mutableListOf(-1,1), SppBallType.smv),
                TestDto(37,"Ребенку не нравилось, что взрослые ходят через ту комнату, где он играю с друзьями.", mutableListOf("ДА", "НЕТ или такого не было"), mutableListOf(-1,1), SppBallType.st),
            TestDto(38,"Проверяя домашнее задание, мы всегда обращали внимание на порядок его выполнения: сначала основные предметы, а затем “второстепенные”, и были недовольны, если он нарушался.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppBallType.sp),
                TestDto(39,"Ребенка раздражало, когда приходилось носить вещи старшей сестры или брата.", mutableListOf("ДА", "НЕТ или такого не было"), mutableListOf(-1,1), SppBallType.smv),
                TestDto(40,"Маленьких детей везде ждут огорчения: даже надевая шапку или завязывая шарф, взрослые умудряются зацепить за ухо или вырвать волосы.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppBallType.sft),
                TestDto(41,"У ребенка вызывало брезгливость, если мама или бабушка настаивали, чтобы он попробовал приготовленную ими еду “поварской” ложкой, которой они пробовали ее сами.", mutableListOf("ДА", "НЕТ или такого не было"), mutableListOf(-1,1), SppBallType.smv),
                TestDto(42,"Ребенок не любил, если у нас оставались на ночь гости, и ему приходилось менять спальное место.", mutableListOf("ДА", "НЕТ или такого не было"), mutableListOf(-1,1), SppBallType.st),
            TestDto(43,"Если ребенок не сделал домашнее задание, но успел выполнить его на перемене, мы никогда его не ругали: “Победителя не судят”.", mutableListOf("ДА", "НЕТ, ругали"), mutableListOf(1,-1), SppBallType.sp),
                TestDto(44,"У ребенка нередко вызывало огорчение, когда ему надевали одежду через голову.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppBallType.sft),
                TestDto(45,"В нашей семье считалось важным тратить деньги не только на самое необходимое, но и на то, чего очень хочется ребенку (дорогие книги и приборы – бинокль, словарь, – без которых можно было вполне обойтись).", mutableListOf("ДА", "НЕТ"), mutableListOf(1,-1), SppBallType.smv)






        )
    }

}
