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
    var ss: Int = 0
    var sts: Int = 0

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
                        SppBallType.ss -> ans.ans?.let { a -> this.ss += it.bal[it.ansList.indexOf(a)] ?: 0 }
                        SppBallType.sts -> ans.ans?.let { a -> this.sts += it.bal[it.ansList.indexOf(a)] ?: 0 }
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
                TestDto(0,"1. У нас в порядке вещей, если мы переключаем телевизор на свой канал, когда ребенок его смотрит.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppBallType.sp),
                TestDto(1,"2. Часто ребенку приходится терпеть, когда родственники тискают или целуют его.", mutableListOf("ДА", "НЕТ или такого не было"), mutableListOf(-1,1), SppBallType.sft),
                TestDto(2,"3. Ребенок обижается, когда его в порядке наказания шлёпают или дают подзатыльники.", mutableListOf("ДА", "НЕТ или такого не было"), mutableListOf(-1,1), SppBallType.sft),
                TestDto(3,"4. Ребенок раздражается, если во время стирки я вытряхиваю его вещи из карманов.", mutableListOf("ДА", "НЕТ или такого не было"), mutableListOf(-1,1), SppBallType.smv),
                TestDto(4,"5. Ребенка никогда не заставляли есть насильно, как других детей.", mutableListOf("ДА", "НЕТ"), mutableListOf(1,-1), SppBallType.sft),
                TestDto(5,"6. Ребенок часто огорчается, когда мы наводим порядок в его игрушках.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppBallType.smv),
                TestDto(6,"7. Для ребенка было неприятным переживанием, когда ему стригли ногти.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppBallType.sft),
                TestDto(7,"8. У ребенка всегда было место (стол, сундучок, коробка), где он мог спрятать дорогие ему предметы.", mutableListOf("ДА", "НЕТ"), mutableListOf(1,-1), SppBallType.st),
                TestDto(8,"9. Помню, что ребенок сильно грустил оттого, что ему не разрешают ложиться спать чуть позже, чем это было принято.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppBallType.sp),
                TestDto(9,"10. Нередко бывает, что нужные ребенку вещи покупаются как поощрение за хорошую учёбу или поведение.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppBallType.smv),
                TestDto(10,"11. Ребенку не нравится, если без разрешения берут его чашку или расческу.", mutableListOf("ДА", "НЕТ или такого не было"), mutableListOf(-1,1), SppBallType.smv),
                TestDto(11,"12. Когда ребенок чувствует себя обиженным, он имеет привычку запираться в ванной или туалете.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppBallType.st),
                TestDto(12,"13. Ребенок расстраивается, когда не может доиграть из-за того, что взрослые зовут его к себе.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppBallType.sp),
                TestDto(13,"14. Случалось, ребенок обижался, когда взрослые начинали серьезный разговор и выставляли его в другую комнату.", mutableListOf("ДА", "НЕТ или такого не было"), mutableListOf(-1,1), SppBallType.st),
                TestDto(14,"15. Даже если подходило время ложиться спать, ребенку обычно разрешено досмотреть любимую передачу.", mutableListOf("ДА", "НЕТ"), mutableListOf(1,-1), SppBallType.sp),
                TestDto(15,"16. Ребенку не нравится семейная традиция донашивать хорошие вещи, доставшиеся от других детей.", mutableListOf("ДА", "НЕТ или такого не было"), mutableListOf(-1,1), SppBallType.smv),
                TestDto(16,"17. Часто случается, что, стесняясь попроситься в туалет, ребенок долго терпит.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppBallType.sft),
                TestDto(17,"18. Ребенок вполне ощутил, что отдать любимую игрушку – одно из самых сильных страданий маленького ребенка.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppBallType.smv),
                TestDto(18,"19. Мы считаем, что можем войти в ванную или туалет, когда там ребенок, и не разрешаем закрывать дверь на замок.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppBallType.st),
                TestDto(19,"20. Мы стараемся делать с ребенком уроки, хотя он уже справляется сам.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppBallType.sp),
                TestDto(20,"21. Когда приготовлена вкусная еда, то близкие ребенку взрослые считают необходимым накормить его ею, даже преодолевая его сопротивление.", mutableListOf("ДА", "НЕТ или такого не было"), mutableListOf(-1,1), SppBallType.sft),
                TestDto(21,"22. Ребенку бы очень хотелось, чтобы на дверях детских комнат были замки, хотя взрослых это обижает.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppBallType.st),
                TestDto(22,"23. Ребенку случалось огорчаться, если мы неожиданно брали его в гости.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppBallType.sp),
                TestDto(23,"24. Ребенок переживал оттого, что взрослые ошибочно думали, будто любую вещь можно заменить другой.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppBallType.smv),
                TestDto(24,"25. Ребенок предпочитает ходить в гости, а не звать гостей к себе.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppBallType.st),
                TestDto(25,"26. Ребенка раздражает, если мы не информируем его о своих планах.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppBallType.sp),
            TestDto(26,"27. Ребенку не нравится, когда взрослые хотят его пощекотать или ущипнуть.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppBallType.sft),
                TestDto(27,"28. Ребенку не нравится, что новых вещей не покупают, пока у него есть старые, но в хорошем состоянии.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppBallType.smv),
                TestDto(28,"29. Как и многие другие дети, ребенок мечтал построить домик из диванных подушек, но ему этого не разрешали.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppBallType.st),
            TestDto(29,"30. Если у ребенка с друзьями возникали общие планы, мы нередко старались их изменить.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppBallType.sp),
                TestDto(30,"31. Если ребенок возвращается из магазина, то может часть сдачи оставить себе.", mutableListOf("ДА", "НЕТ"), mutableListOf(1,-1), SppBallType.smv),
            TestDto(31,"32. У нас принято, что мы всегда знаем распорядок дня ребенка.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppBallType.sp),
                TestDto(32,"33. Ребенку часто бывает неприятно, когда взрослые дотрагиваются до него.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppBallType.sft),
                TestDto(33,"34. Ребенка раздражает, если в его комнате наводят порядок.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppBallType.st),
            TestDto(34,"35. Во время обеда, если суп горячий, ребенок может сначала съесть второе, и мы это не запрещаем.", mutableListOf("ДА", "НЕТ"), mutableListOf(1,-1), SppBallType.sp),
                TestDto(35,"36. Посещая врача, ребенок боится не боли, а того, что чужой человек будет его трогать.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppBallType.sft),
                TestDto(36,"37. Наверное, мой ребенок собственник: он постоянно раздражается оттого, что кто-то пользовался его вещами.", mutableListOf("ДА", "НЕТ или такого не было"), mutableListOf(-1,1), SppBallType.smv),
                TestDto(37,"38. Ребенку не нравится, что взрослые ходят через ту комнату, где он играет с друзьями.", mutableListOf("ДА", "НЕТ или такого не было"), mutableListOf(-1,1), SppBallType.st),
            TestDto(38,"39. Проверяя домашнее задание, мы всегда обращаем внимание на порядок его выполнения: сначала основные предметы, а затем “второстепенные”, и бывали недовольны, если он нарушался.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppBallType.sp),
                TestDto(39,"40. Ребенка раздражает, когда приходится носить вещи старшей сестры или брата.", mutableListOf("ДА", "НЕТ или такого не было"), mutableListOf(-1,1), SppBallType.smv),
                TestDto(40,"41. Маленьких детей везде ждут огорчения: даже надевая шапку или завязывая шарф, взрослые умудряются зацепить за ухо или вырвать волосы.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppBallType.sft),
                TestDto(41,"42. У ребенка вызывало брезгливость, если я или бабушка настаивали, чтобы он попробовал приготовленную нами еду “поварской” ложкой, которой мы пробуем ее сами.", mutableListOf("ДА", "НЕТ или такого не было"), mutableListOf(-1,1), SppBallType.smv),
                TestDto(42,"43. Ребенок не любит, если у нас остаются на ночь гости, и ему приходится менять спальное место.", mutableListOf("ДА", "НЕТ или такого не было"), mutableListOf(-1,1), SppBallType.st),
            TestDto(43,"44. Если ребенок не сделал домашнее задание, но успел выполнить его на перемене, мы никогда его не ругаем: “Победителя не судят”.”.", mutableListOf("ДА", "НЕТ, ругали"), mutableListOf(1,-1), SppBallType.sp),
                TestDto(44,"45. У ребенка нередко вызывает огорчение, когда ему надевают одежду через голову.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppBallType.sft),
                TestDto(45,"46. В нашей семье считается важным тратить деньги не только на самое необходимое, но и на то, чего очень хочется ребенку (дорогие книги и приборы – бинокль, словарь, – без которых можно было вполне обойтись).", mutableListOf("ДА", "НЕТ"), mutableListOf(1,-1), SppBallType.smv),
                TestDto(46,"47. Решение о проведении каникул и выходных чаще принимается без ребенка.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppBallType.sts),
                TestDto(47,"48. Бывает, что ребенок носит одежду, в которой чувствует себя посмешищем, и боится, что его будут дразнить.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppBallType.sts),
                TestDto(48,"49. Обычно мы не запрещаем слушать музыку, которая нравится ребенку, даже если она нас раздражает.", mutableListOf("ДА", "НЕТ"), mutableListOf(1,-1), SppBallType.sts),
                TestDto(49,"50. Случалось, что детский праздник был для ребенка испорчен, если ребенок был одет не так, как хотелось.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppBallType.sts),
                TestDto(50,"51. Если друзья предлагали ребенку переночевать у них, мы обычно не возражали.", mutableListOf("ДА", "НЕТ"), mutableListOf(1,-1), SppBallType.ss),
                TestDto(51,"52. Ребенку обычно удаётся устроить детский праздник так, как хотелось.", mutableListOf("ДА", "НЕТ"), mutableListOf(1,-1), SppBallType.sts),
                TestDto(52,"53. Ребенку часто хочется поиграть с детьми, которые вместе с ним ходят в кружок, но обычно мы торопимся, и ему это не удаётся.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppBallType.ss),
                TestDto(53,"54. При покупке вещей мы всегда учитываем мнение ребенка.", mutableListOf("ДА", "НЕТ"), mutableListOf(1,-1), SppBallType.sts),
                TestDto(54,"55. Даже если нам некогда, мы находим время, чтобы ребенок поиграл с детьми, которые ему симпатичны.", mutableListOf("ДА", "НЕТ"), mutableListOf(1,-1), SppBallType.ss),
                TestDto(55,"56. Ребенка нередко заставляют есть калорийную, но невкусную пищу.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppBallType.sts),
                TestDto(56,"57. Проблема “отцов и детей” у нас не существует, так как мы с детства уважаем мнение ребенка.", mutableListOf("ДА", "НЕТ"), mutableListOf(1,-1), SppBallType.sts),
                TestDto(57,"58. Мы спокойно принимаем тот факт, что знаем не всех друзей ребенка.", mutableListOf("ДА", "НЕТ"), mutableListOf(1,-1), SppBallType.ss),
                TestDto(58,"59. Существуют телепередачи, которые ребенок не может смотреть без разрешения.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppBallType.sts),
                TestDto(59,"60. Ребенок всегда уверен, что, когда он с кем-то разговаривает по телефону, никто не прервет и не подслушает его беседы.", mutableListOf("ДА", "НЕТ"), mutableListOf(1,-1), SppBallType.ss),
                TestDto(60,"61. Мы пресекаем попытки ребенка украсить себя так, как не было принято в наше время (пирсинг, татуаж, прически).", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppBallType.sts),
                TestDto(61,"62. Если у ребенка появляется новый знакомый, он должен обязательно показать его нам.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppBallType.ss),
                TestDto(62,"63. Мы всегда демонстрируем свое несогласие с попытками ребенка соответствовать молодежной культуре.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppBallType.sts),
                TestDto(63,"64. У ребенка было увлечение (кружок, спортивная или художественная школа), которое не реализовалось, потому что мы были против.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppBallType.sts),
                TestDto(64,"65. Случалось, ребенку навязывали общение с братом или сестрой, даже когда ему этого не хотелось.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppBallType.ss),
                TestDto(65,"66. Ребенку случалось раздражать нас, если его мнение не совпадало с нашим.", mutableListOf("ДА", "НЕТ"), mutableListOf(-1,1), SppBallType.sts),
                TestDto(66,"67. Мы не настаиваем, чтобы ребенок “пошел по нашим стопам”.", mutableListOf("ДА", "НЕТ"), mutableListOf(1,-1), SppBallType.sts)

        )
    }

}
