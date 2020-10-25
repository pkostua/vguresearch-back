package com.vgu.research.entity.tests

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
class AnketaQuestion(
    var type: AnketaQuestionType = AnketaQuestionType.Q1,
    var ans: String = "",
    @JsonIgnore @ManyToOne var anketa: Anketa? = null
    ){
    @Id @GeneratedValue var id: Int =0
}

enum class AnketaQuestionType (var title: String, var ansList:List<String>){
    Q1("У вас есть отдельная детская комната?", listOf("Да", "Не совсем", "Нет")),
    Q2("У ребенка есть любимая игрушка?", listOf("Да", "Не совсем", "Нет")),
    Q3("В ванной комнате ребёнок легко дотягивается до полотенца?", listOf("Да", "Не совсем", "Нет")),
    Q4("Ваш ребёнок легко делится игрушками?", listOf("Да", "Не совсем", "Нет")),
    Q5("Ребенок может сам подкрепиться, если проголодался, а до приема пищи ещё долго ждать?", listOf("Да", "Не совсем", "Нет")),
    Q6("Ваш ребёнок может сам доставать себе одежду из шкафа (свободно, без помощи стула)?", listOf("Да", "Не совсем", "Нет")),
    Q7("Ваш ребёнок любит обниматься с вами?", listOf("Да", "Не совсем", "Нет")),
    Q8("Вы затачиваете ребёнку карандаши?", listOf("Да", "Не совсем", "Нет")),
    Q9("У вашего ребёнка есть своё личное полотенце для тела?", listOf("Да", "Не совсем", "Нет")),
    Q10("У ребенка есть возможность делать уроки в тишине?", listOf("Да", "Не совсем", "Нет")),
    Q11("Ребенок может сам включить свет в туалете?", listOf("Да", "Не совсем", "Нет")),
    Q12("Ваш ребенок спит один в кровати?", listOf("Да", "Не совсем", "Нет")),
    Q13("Вы моете ребёнку уличную обувь?", listOf("Да", "Не совсем", "Нет")),
    Q14("У вашего ребёнка за обеденным столом стул, предназначенный для детей?", listOf("Да", "Не совсем", "Нет")),
    Q15("Высота вешалки в прихожей позволяет ребенку самостоятельно вешать и снимать свою верхнюю одежду без помощи стула?", listOf("Да", "Не совсем", "Нет")),
    Q16("У ребенка есть установленный час для выполнения домашнего задания?", listOf("Да", "Не совсем", "Нет")),
    Q17("У вас запирается туалет?", listOf("Да", "Не совсем", "Нет")),
    Q18("Ребёнок легко может сам достать себе столовую посуду?", listOf("Да", "Не совсем", "Нет")),
    Q19("Ваш ребёнок самостоятельно одевается в школу?", listOf("Да", "Не совсем", "Нет")),
    Q20("Ваш ребёнок любит ходить с вами за руку?", listOf("Да", "Не совсем", "Нет")),
    Q21("Часто приходится очень торопиться с вашим ребенком, чтобы не опоздать?", listOf("Да", "Не совсем", "Нет")),
    Q22("У вашего ребенка есть своя полка для книг и учебников до которой он легко дотягивается?", listOf("Да", "Не совсем", "Нет")),
    Q23("У вашего ребёнка есть свои столовые приборы (например, кружка)?", listOf("Да", "Не совсем", "Нет")),
    Q24("Ваш ребёнок может один выходить на улицу?", listOf("Да", "Не совсем", "Нет")),
    Q25("Ваш ребёнок делает уроки на стуле, предназначенном для детей?", listOf("Да", "Не совсем", "Нет")),
    Q26("В ванной комнате есть подставка для ребёнка у раковины?", listOf("Да", "Не совсем", "Нет")),
    Q27("Ваш ребёнок сам несёт свой портфель в школу или из школы, если идёт со взрослым?", listOf("Да", "Не совсем", "Нет")),
    Q28("У ребенка есть установленный час отхода ко сну?", listOf("Да", "Не совсем", "Нет")),
    Q29("Ваш ребёнок сам собирает свой портфель в школу?", listOf("Да", "Не совсем", "Нет")),
    Q30("Ребёнок может заходить в любую комнату в доме?", listOf("Да", "Не совсем", "Нет")),
    Q31("Вы предупреждаете ребёнка о поездках за один день и больше?", listOf("Да", "Не совсем", "Нет")),
    Q32("Вы помогаете принимать ванну/душ вашему ребенку?", listOf("Да", "Не совсем", "Нет")),
    Q33("Вы (или другие взрослые вашей семьи) встречаете ребёнка у школы после занятий?", listOf("Да", "Не совсем", "Нет")),
    Q34("Ваш ребенок сам выбирает какие игрушки купить?", listOf("Да", "Не совсем", "Нет")),
    Q35("Если во время разговора с ребёнком, вы заденете пальцем кончик его носа, он улыбнётся?", listOf("Да", "Не совсем", "Нет")),

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
