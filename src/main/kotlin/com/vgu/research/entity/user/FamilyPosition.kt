package com.vgu.research.entity.user

enum class FamilyPosition (val title: String, val isAdult: Boolean) {
    BABY ("малыш", false),
    SADIK ("ходит в садик", false),
    FIRST_CLASS ("1 класс", false),
    SECOND_CLASS ("2 класс", false),
    THIRD_CLASS ("3 класс", false),
    FORTH_CLASS ("4 класс", false),
    FIVE_CLASS_AND_MORE ("5 класс и старше", false),
    STUDENT ("студент", false),

    MAMA ("мама", true),
    PAPA ("папа", true),
    GANNY ("бабушка", true),
    GRANDFATHER ("дедеушка", true),
    ANT ("тётя", true),
    ANCL ("дядя", true),
    OTHER ("другой родственник", true),
    BROTHER ("брат старше 18 лет", true),
    SISTER ("сестра старше 18 лет", true),











}

