package com.vgu.research.entity.tests

import com.vgu.research.dto.SppBallType
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
    var sp: Int = 0 //Суверенность привычек

    @ManyToOne
    var user: User? = null

    @ManyToOne
    var parent: FamilyMember? = null

    @ManyToOne
    var child: FamilyMember? = null

    constructor(ansList: MutableList<TestDtoAns>, user: User?, parent: FamilyMember?, child: FamilyMember?):this(){
        this.user = user
        this.parent = parent
        this.child = child
        ansList.forEach{ans->
            data.find { it.id == ans.id }?.let {
                when(it.type){
                    SppBallType.smv-> this.smv += it.bal[it.ansList.indexOf(ans.ans)]?:0
                    SppBallType.st-> this.st += it.bal[it.ansList.indexOf(ans.ans)]?:0
                    SppBallType.sft-> this.sft += it.bal[it.ansList.indexOf(ans.ans)]?:0
                    SppBallType.sp-> this.sp += it.bal[it.ansList.indexOf(ans.ans)]?:0
                    else->{}
                }
            }
        }
    }


    companion object{
        public val data: MutableList<TestDto>
        get()= mutableListOf(
                TestDto(0,"У нас было в порядке вещей, если родители переключали телевизор на свой канал, когда я его смотрел", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppBallType.sp),
                TestDto(1,"Часто мне приходилось терпеть, когда родственники тискали или целовали меня.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppBallType.sft),
                TestDto(2,"Я обижался, когда меня в порядке наказания шлёпали или давали подзатыльники.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppBallType.sft),
                TestDto(3,"Я раздражался, если во время стирки мама вытряхивала мои вещи из карманов.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppBallType.smv),
                TestDto(4,"Меня никогда не заставляли есть насильно, как других детей.", mutableListOf("ДА", "НЕТ"), mutableListOf(1,-1), SppBallType.sft),
                TestDto(5,"Я часто огорчался, когда родители наводили порядок в моих игрушках.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppBallType.smv),
                TestDto(6,"Для меня было неприятным переживанием, когда мне стригли ногти.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppBallType.sft),
                TestDto(7,"У меня всегда было место (стол, сундучок, коробка), где я мог спрятать дорогие мне предметы.", mutableListOf("ДА", "НЕТ"), mutableListOf(1,-1), SppBallType.st),
                TestDto(8,"Помню, что я сильно грустил оттого, что мне не разрешали ложиться спать чуть позже, чем это было принято.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppBallType.sp),
                TestDto(9,"Нередко бывало, что нужные мне вещи покупались как поощрение за хорошую учёбу или поведение.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppBallType.smv),
                TestDto(10,"Мне не нравилось, если без разрешения брали мою чашку или расческу.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppBallType.smv),
                TestDto(11,"Когда я чувствовал себя обиженным, я имел привычку запираться в ванной или туалете.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppBallType.st),
                TestDto(12,"Я расстраивался, когда не мог доиграть из-за того, что взрослые звали меня к себе.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppBallType.sp),
                TestDto(13,"Случалось, я обижался, когда взрослые начинали серьезный разговор и выставляли меня в другую комнату.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppBallType.st),
                TestDto(14,"Даже если подходило время ложиться спать, мне обычно разрешали досмотреть любимую передачу.", mutableListOf("ДА", "НЕТ"), mutableListOf(1,-1), SppBallType.sp),
                TestDto(15,"Мне не нравилась семейная традиция донашивать хорошие вещи, доставшиеся от других детей.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppBallType.smv),
                TestDto(16,"Часто случалось, что, стесняясь попроситься в туалет, я долго терпел.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppBallType.sft),
                TestDto(17,"Я вполне ощутил, что отдать любимую игрушку – одно из самых сильных страданий маленького ребенка.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppBallType.smv),
                TestDto(18,"Взрослые почему-то считали, что могут войти в ванную или туалет, когда там ребенок, и не разрешали закрывать дверь на замок.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppBallType.st),
                TestDto(19,"Родители старались делать со мной уроки, когда я уже справлялся сам.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppBallType.sp),
                TestDto(20,"Когда была приготовлена вкусная еда, то близкие мне взрослые считали необходимым накормить меня ею, даже преодолевая мое сопротивление.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppBallType.sft),
                TestDto(21,"Мне бы очень хотелось, чтобы на дверях детских комнат были замки, хотя взрослых это обижает.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppBallType.st),
                TestDto(22,"Мне случалось огорчаться, если родители неожиданно брали меня в гости.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppBallType.sp),
                TestDto(23,"Я переживал оттого, что взрослые ошибочно думали, будто любую вещь можно заменить другой.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppBallType.smv),
                TestDto(24,"Я предпочитаю ходить в гости, а не звать гостей к себе.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppBallType.st),
                TestDto(25,"Меня раздражало, если взрослые не информировали меня о своих планах.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppBallType.sp),
            TestDto(26,"Мне не нравилось, когда взрослые любили меня пощекотать или ущипнуть.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppBallType.sft),
                TestDto(27,"Мне не нравилось, что новых вещей не покупали, пока у нас были старые, но в хорошем состоянии.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppBallType.smv),
                TestDto(28,"Как и многие другие дети, я мечтал построить домик из диванных подушек, но мне этого не удавалось.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppBallType.st),
            TestDto(29,"Если у нас с друзьями возникали общие планы, наши родители нередко старались их изменить.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppBallType.sp),
                TestDto(30,"Если я возвращался из магазина, то мог часть сдачи оставить себе.", mutableListOf("ДА", "НЕТ"), mutableListOf(1,-1), SppBallType.smv),
            TestDto(31,"У нас было принято, чтобы родители всегда знали мой распорядок дня.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppBallType.sp),
                TestDto(32,"Мне часто бывало неприятно, когда взрослые дотрагивались до меня.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppBallType.sft),
                TestDto(33,"Меня раздражало, если в моей комнате наводили порядок.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppBallType.st),
            TestDto(34,"Во время обеда, если суп был горячим, я мог сначала съесть второе, и родители это не запрещали.", mutableListOf("ДА", "НЕТ"), mutableListOf(1,-1), SppBallType.sp),
                TestDto(35,"Посещая врача, я боялся не боли, а того, что чужой человек будет меня трогать.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppBallType.sft),
                TestDto(36,"Наверное, я собственник: я постоянно раздражался оттого, что кто-то пользовался моими вещами.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppBallType.smv),
                TestDto(37,"Мне не нравилось, что взрослые ходят через ту комнату, где я играю с друзьями.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppBallType.st),
            TestDto(38,"Проверяя домашнее задание, родители всегда обращали внимание на порядок его выполнения: сначала основные предметы, а затем “второстепенные”, и были недовольны, если он нарушался.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppBallType.sp),
                TestDto(39,"Меня раздражало, когда приходилось в детстве носить вещи старшей сестры или брата.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppBallType.smv),
                TestDto(40,"Маленьких детей везде ждут огорчения: даже надевая шапку или завязывая шарф, взрослые умудряются зацепить за ухо или вырвать волосы.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppBallType.sft),
                TestDto(41,"У меня вызывало брезгливость, если мама или бабушка настаивали, чтобы я попробовал приготовленную ими еду “поварской” ложкой, которой они пробовали ее сами.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppBallType.smv),
                TestDto(42,"Я не любил, если у нас оставались на ночь гости, и мне приходилось менять спальное место.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppBallType.st),
            TestDto(43,"Если я не сделал домашнее задание, но успел выполнить его на перемене, родители никогда меня не ругали: “Победителя не судят”.", mutableListOf("ДА", "НЕТ"), mutableListOf(1,-1), SppBallType.sp),
                TestDto(44,"У меня нередко вызывало огорчение, когда мне надевали одежду через голову.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppBallType.sft),
                TestDto(45,"В нашей семье считалось важным тратить деньги не только на самое необходимое, но и на то, чего очень хочется ребенку (дорогие книги и приборы – бинокль, словарь, – без которых можно было вполне обойтись).", mutableListOf("ДА", "НЕТ"), mutableListOf(1,-1), SppBallType.smv)






        )
    }

}
