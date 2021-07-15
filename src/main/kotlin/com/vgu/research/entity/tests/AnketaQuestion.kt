package com.vgu.research.entity.tests

import com.fasterxml.jackson.annotation.JsonIgnore
import com.vgu.research.dto.SppBallType
import javax.persistence.*

@Entity
class AnketaQuestion(
    var type: AnketaQuestionType = AnketaQuestionType.Q1,
    var ans: String = "",
    @JsonIgnore @ManyToOne var anketa: Anketa? = null
    ){
    @Id @GeneratedValue var id: Int =0
}

enum class AnketaQuestionType (var title: String, var ansList:List<String>, var ballList: List<Double>, var type: SppBallType){
    Q1("1. Игрушки и вещи вашего ребенка, кроме его личного места или комнаты, можно найти в любом месте дома?", listOf("Да", "Не совсем", "Нет"), listOf(1.0, 0.5, 0.0), SppBallType.smv),
    Q2("2. Ребенку нравится еда, которую вы готовите для семьи?", listOf("Да", "Не совсем", "Нет"), listOf(1.0, 0.5, 0.0), SppBallType.sft),
    Q3("3. У ребенка есть любимая игрушка?", listOf("Да", "Не совсем", "Нет"), listOf(1.0, 0.5, 0.0), SppBallType.smv),
    Q4("4. У вас есть отдельная детская комната?", listOf("Да", "Не совсем", "Нет"), listOf(1.0, 0.5, 0.0), SppBallType.st),
    Q5("5. У вас с ребенком близкие дружественные отношения?", listOf("Да", "Не совсем", "Нет"), listOf(1.0, 0.5, 0.0), SppBallType.ss),
    Q6("6. У вашего ребенка собственная кровать, которая не используется никак больше?", listOf("Да", "Не совсем", "Нет"), listOf(1.0, 0.5, 0.0), SppBallType.st),
    Q7("7. Ребенок сам справляется с принятием ванны/душа?", listOf("Да", "Не совсем", "Нет"), listOf(1.0, 0.5, 0.0), SppBallType.sft),
    Q8("8. У вашего ребенка есть его личный стол или шкаф, где только его вещи?", listOf("Да", "Не совсем", "Нет"), listOf(1.0, 0.5, 0.0), SppBallType.st),
    Q9("9. Часто ребенок обижается на ваши слова или действия", listOf("Да", "Не совсем", "Нет"), listOf(0.0, 0.5, 1.0), SppBallType.ss),
    Q10("10. У вашего ребёнка есть на кухне свои столовые приборы (например, кружка) или свой стул за обеденным столом?", listOf("Да", "Не совсем", "Нет"), listOf(1.0, 0.5, 0.0), SppBallType.smv),
    Q11("11. Ребёнку можно запираться в туалете?", listOf("Да", "Не совсем", "Нет"), listOf(1.0, 0.5, 0.0), SppBallType.sft),
    Q12("12. У ребенка есть установленный час отхода ко сну?", listOf("Да", "Не совсем", "Нет"), listOf(1.0, 0.5, 0.0), SppBallType.sp),
    Q13("13. Ребёнок может заходить в любую комнату в доме?", listOf("Да", "Не совсем", "Нет"), listOf(1.0, 0.5, 0.0), SppBallType.st),
    Q14("14. Ваш ребёнок любит обниматься с вами?", listOf("Да", "Не совсем", "Нет"), listOf(1.0, 0.5, 0.0), SppBallType.sft),
    Q15("15. Ваш ребенок может приводить друзей в дом?", listOf("Да", "Не совсем", "Нет"), listOf(1.0, 0.5, 0.0), SppBallType.ss),
    Q16("16. Ваш ребенок легко занимает себя делами и играми, даже если он один?", listOf("Да", "Не совсем", "Нет"), listOf(1.0, 0.5, 0.0), SppBallType.sts),
    Q17("17. Ваш ребёнок сам собирает свой портфель в школу?", listOf("Да", "Не совсем", "Нет"), listOf(1.0, 0.5, 0.0), SppBallType.smv),
    Q18("18. У вас часто бывают споры с ребенком по поводу того, что ему надеть?", listOf("Да", "Не совсем", "Нет"), listOf(0.0, 0.5, 1.0), SppBallType.smv),
    Q19("19. У ребенка есть установленный час для выполнения домашнего задания?", listOf("Да", "Не совсем", "Нет"), listOf(1.0, 0.5, 0.0), SppBallType.sp),
    Q20("20. Ребенок переживает из-за недостатков своей внешности?", listOf("Да", "Не совсем", "Нет"), listOf(0.0, 0.5, 1.0), SppBallType.sft),
    Q21("21. Есть такие игрушки или вещи, которые подходят ребёнку по возрасту, но вы не покупаете их, потому что они вам не нравятся?", listOf("Да", "Не совсем", "Нет"), listOf(0.0, 0.5, 1.0), SppBallType.sts),
    Q22("22. Вас раздражает, что ваш ребенок слишком быстрый/медленный?", listOf("Да", "Не совсем", "Нет"), listOf(0.0, 0.5, 1.0), SppBallType.sp),
    Q23("23. Ваш ребенок соглашается не со всеми вашими предложениями и может предложить альтернативу (например, выбор работы по дому или заменить поездку к родственникам на другие занятия)?", listOf("Да", "Не совсем", "Нет"), listOf(1.0, 0.5, 0.0), SppBallType.sts),
    Q24("24. В выходные дни ребёнок встаёт, когда захочет?", listOf("Да", "Не совсем", "Нет"), listOf(1.0, 0.5, 0.0), SppBallType.sp),
    Q25("25. У ребенка есть возможность делать уроки в тишине?", listOf("Да", "Не совсем", "Нет"), listOf(1.0, 0.5, 0.0), SppBallType.st),
    Q26("26. В учебное время весь день ребенка занят важными занятиями?", listOf("Да", "Не совсем", "Нет"), listOf(0.0, 0.5, 1.0), SppBallType.sp),
    Q27("27. Ваш ребенок часто требователен в разговоре с членами семьи?", listOf("Да", "Не совсем", "Нет"), listOf(0.0, 0.5, 1.0), SppBallType.ss),
    Q28("28. Ваш ребенок достаточно ответственный для своего возраста?", listOf("Да", "Не совсем", "Нет"), listOf(1.0, 0.5, 0.0), SppBallType.sts),
    Q29("29. У вас есть передачи, мультфильмы или музыка (подходящие по возрасту), которые запрещено включать ребёнку, потому что они вам не нравятся.", listOf("Да", "Не совсем", "Нет"), listOf(0.0, 0.5, 1.0), SppBallType.sts),
    Q30("30. Если во время разговора с ребёнком, вы заденете пальцем кончик его носа, он улыбнётся?", listOf("Да", "Не совсем", "Нет"), listOf(1.0, 0.5, 0.0), SppBallType.ss),


}

data class AnketaQuestionTypeDto(
        val id: AnketaQuestionType,
        val text: String,
        var ansList:List<String>,
        var ans: String? = null
){
    constructor(type: AnketaQuestionType): this(type, type.title, type.ansList)
}

data class AnketaQuestionTypeDtoWrapper(
        val data: List<AnketaQuestionTypeDto>
)
