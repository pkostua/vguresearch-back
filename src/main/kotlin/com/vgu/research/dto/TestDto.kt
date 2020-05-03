package com.vgu.research.dto

data class TestDto(
        var id: Int,
        var text: String,
        var ansList: MutableList<String> = mutableListOf("ДА", "НЕТ"),
        var bal:MutableList<Int> = mutableListOf(0,1),
        var type: SppChildrenBallType = SppChildrenBallType.sft
)

enum class SppChildrenBallType{
    sft, // суверенность физического тела
    st, //Суверенность территории
    smv, //Суверенность мира вещей
    sp //суверенность привычек
}

data class TestDtoAns(
        var id: Int,
        var ans: String
)
data class TestDtoAnsWrapper(
        var data: MutableList<TestDtoAns>
)
