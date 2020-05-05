package com.vgu.research.entity.tests

import com.vgu.research.dto.SppBallType
import com.vgu.research.dto.TestDto
import com.vgu.research.dto.TestDtoAns
import com.vgu.research.entity.user.User
import java.util.*
import javax.persistence.*


@Entity
class SppAdult() {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long =0
    var date: Date = Date()
    var gpl: Long =0// (7) гиперпротекция
    var gmin: Long =0// (8) гипопротекция
    var ypl: Long =0// (8) потворствование
    var ymin: Long =0// (4) игнорирование потребностей
    var tpl: Long =0// (4) чрезмерность требований-обязанностей
    var tmin: Long =0// (4) недостаточность требований-обязанностей
    var zpl: Long =0// (4) чрезмерность требований-запретов
    var zmin: Long =0// (3) недостаточность требований-запретов
    var cpl: Long =0// (4) чрезмерность санкций
    var smin: Long =0// (4) минимальность санкций
    var n: Long =0// (5) неустойчивость стиля воспитания
    var rrc: Long =0// (6) расширение сферы родительских чувств
    var pdk: Long =0// (4) предпочтение в подростке детских качеств
    var vn: Long =0 // (5) воспитательная неуверенность родителя
    var fy: Long =0// (6) фобия утраты ребенка
    var nrc: Long =0// (7) неразвитость родительских чувств
    var pnk: Long =0// (4) проекция на ребёнка собственных нежелаемых качеств
    var vk: Long =0// (4) внесение конфликта между супругами в сферу воспитания
    var pzk: Long =0// (4) предпочтение женских качеств
    var pmk: Long =0// (4) предпочтение мужских качеств

    @ManyToOne
    var user: User? = null

    constructor(ansList: MutableList<TestDtoAns>, user: User?):this(){
        this.user = user
        ansList.forEach{ans->
            data.find { it.id == ans.id }?.let {
                when(it.type){
                    SppBallType.gpl-> this.gpl += it.bal[it.ansList.indexOf(ans.ans)]?:0
                    SppBallType.gmin-> this.gmin += it.bal[it.ansList.indexOf(ans.ans)]?:0
                    SppBallType.ypl-> this.ypl += it.bal[it.ansList.indexOf(ans.ans)]?:0
                    SppBallType.ymin-> this.ymin += it.bal[it.ansList.indexOf(ans.ans)]?:0
                    SppBallType.tpl-> this.tpl += it.bal[it.ansList.indexOf(ans.ans)]?:0
                    SppBallType.tmin-> this.tmin += it.bal[it.ansList.indexOf(ans.ans)]?:0
                    SppBallType.zpl-> this.zpl += it.bal[it.ansList.indexOf(ans.ans)]?:0
                    SppBallType.zmin-> this.zmin += it.bal[it.ansList.indexOf(ans.ans)]?:0
                    SppBallType.cpl-> this.cpl += it.bal[it.ansList.indexOf(ans.ans)]?:0
                    SppBallType.smin-> this.smin += it.bal[it.ansList.indexOf(ans.ans)]?:0
                    SppBallType.n-> this.n += it.bal[it.ansList.indexOf(ans.ans)]?:0
                    SppBallType.rrc-> this.rrc += it.bal[it.ansList.indexOf(ans.ans)]?:0
                    SppBallType.pdk-> this.pdk += it.bal[it.ansList.indexOf(ans.ans)]?:0
                    SppBallType.vn-> this.vn += it.bal[it.ansList.indexOf(ans.ans)]?:0
                    SppBallType.fy-> this.fy += it.bal[it.ansList.indexOf(ans.ans)]?:0
                    SppBallType.nrc-> this.nrc += it.bal[it.ansList.indexOf(ans.ans)]?:0
                    SppBallType.pnk-> this.pnk += it.bal[it.ansList.indexOf(ans.ans)]?:0
                    SppBallType.vk-> this.vk += it.bal[it.ansList.indexOf(ans.ans)]?:0
                    SppBallType.pzk-> this.pzk += it.bal[it.ansList.indexOf(ans.ans)]?:0
                    SppBallType.pmk-> this.pmk += it.bal[it.ansList.indexOf(ans.ans)]?:0
                    else->{}
                }
            }
        }
    }


    companion object{
        public val data: MutableList<TestDto>
        get()= mutableListOf(
                TestDto(0,"Всё, что я делаю, я делаю ради моего сына (дочери)", mutableListOf("ДА", "НЕТ", "не знаю"), mutableListOf(1,0,0), SppBallType.gpl),
                TestDto(1,"У меня часто не хватает времени позаниматься с сыном (дочерью) – пообщаться, поиграть.", mutableListOf("ДА", "НЕТ", "не знаю"), mutableListOf(1,0,0), SppBallType.gmin),
                TestDto(2,"Мне приходится разрешать моему ребёнку такие вещи, которые не разрешают многие другие родители.", mutableListOf("ДА", "НЕТ", "не знаю"), mutableListOf(1,0,0), SppBallType.ypl),
                TestDto(3,"Не люблю, когда сын (дочь) приходит ко мне с вопросами. Лучше, чтобы догадался сам (сама).", mutableListOf("ДА", "НЕТ", "не знаю"), mutableListOf(1,0,0), SppBallType.ymin),
                TestDto(4,"Наш ребёнок имеет больше обязанностей – в уходе за собой, поддержании порядка, чем большинство детей его возраста.", mutableListOf("ДА", "НЕТ", "не знаю"), mutableListOf(1,0,0), SppBallType.tpl),
                TestDto(5,"Моего ребёнка трудно заставить что-нибудь сделать, чего он не любит.", mutableListOf("ДА", "НЕТ", "не знаю"), mutableListOf(1,0,0), SppBallType.tmin),
                TestDto(6,"Всегда лучше, если дети не думают о том, правильно ли поступают их родители.", mutableListOf("ДА", "НЕТ", "не знаю"), mutableListOf(1,0,0), SppBallType.zpl),
                TestDto(7,"Мой сын (дочь) легко нарушает запреты.", mutableListOf("ДА", "НЕТ", "не знаю"), mutableListOf(1,0,0), SppBallType.zmin),
                TestDto(8,"Если хочешь, чтобы твой(я) сын (дочь) стал(а) человеком, не оставляй безнаказанным ни одного его (ее) плохого поступка.", mutableListOf("ДА", "НЕТ", "не знаю"), mutableListOf(1,0,0), SppBallType.cpl),
                TestDto(9,"Если только возможно, стараюсь не наказывать сына (дочь).", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.smin),
                TestDto(10,"Когда я в хорошем настроении, нередко прощаю своему сыну (дочери) то, за что в другое время наказал бы.", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.n),
                TestDto(11,"Я люблю своего сына (дочь) больше, чем люблю (любила) супруга. ", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.rrc),
                TestDto(12,"Младшие дети мне нравятся больше, чем старшие.", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.pdk),
                TestDto(13,"Если мой сын (дочь) подолгу упрямится или злится, у меня бывает чувство, что я поступил(а) по отношению к нему (ней) неправильно.", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.vn),
                TestDto(14,"У нас долго не было ребёнка, хотя мы его очень ждали.", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.fy),
                TestDto(15,"Общение с детьми, в общем-то, утомительное дело.", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.nrc),
                TestDto(16,"У моего сына (дочери) есть некоторые качества, которые выводят меня из себя.", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.pnk),
                TestDto(17,"Воспитание моего сына (дочери) шло бы гораздо лучше, если бы мой(я) муж (жена) не мешал бы мне.", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.vk),
                TestDto(18,"Большинство мужчин легкомысленнее, чем женщины.", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.pzk),
                TestDto(19,"Большинство женщин легкомысленнее, чем мужчины.", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.pmk),
                TestDto(20,"Мой сын (дочь) для меня самое главное в жизни.", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.gpl),
                TestDto(21,"Часто бывает, что я не знаю, что делает в данный момент мой ребёнок.", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.gmin),
                TestDto(22,"Если игрушка ребёнку нравится, я куплю ее, сколько бы она не стоила.", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.ypl),
                TestDto(23,"Мой сын (дочь) непонятлив(а). Легче самому два раза сделать, чем один раз объяснить ему (ей).", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.ymin),
                TestDto(24,"Моему сыну (дочери) нередко приходится (или приходилось раньше) присматривать за младшим братом (сестрой).", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.tpl),
                TestDto(25,"Нередко бывает так: напоминаю, напоминаю сыну (дочери) сделать что-нибудь, а потом плюну и сделаю сам(а).", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.tmin),
                TestDto(26,"Родители ни в коем случае не должны допускать, чтобы дети подмечали их слабости и недостатки.", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.zpl),
                TestDto(27,"Мой сын (дочь) сам(а) решает, с кем ему (ей) играть.", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.zmin),
                TestDto(28,"Дети должны не только любить своих родителей, но и боятся их .", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.cpl),
                TestDto(29,"Я очень редко ругаю сына (дочь).", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.smin),
                TestDto(30,"В нашей строгости к сыну (дочери) бывают большие колебания. Иногда мы очень строги, а иногда всё разрешаем.", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.n),
                TestDto(31,"Мы с ребёнком понимаем друг друга лучше, чем мы с супругом.", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.rrc),
                TestDto(32,"Меня огорчает, что мой сын (дочь) слишком быстро становится взрослым.", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.pdk),
                TestDto(33,"Если ребёнок упрямится, потому что плохо себя чувствует, лучше всего сделать так, как он хочет.", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.vn),
                TestDto(34,"Мой ребёнок растет слабым и болезненным.", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.fy),
                TestDto(35,"Если бы у меня не было детей, я бы добился (добилась) в жизни гораздо большего", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.nrc),
                TestDto(36,"У моего сына (дочери) есть недостатки, которые не исправляются, хотя я упорно с ними борюсь.", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.pnk),
                TestDto(37,"Нередко бывает, что когда я наказываю моего сына (дочь), мой муж (жена) тут же начинает упрекать меня в излишней строгости и утешать его (ее).", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.vk),
                TestDto(38,"Мужчины более склонны к супружеской измене, чем женщины.", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.pzk),
                TestDto(39,"Женщины более склонны к супружеской измене, чем мужчины.", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.pmk),
                TestDto(40,"Заботы о сыне (дочери) занимают большую часть моего времени.", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.gpl),
                TestDto(41,"Мне много раз пришлось пропустить родительское собрание.", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.gmin),
                TestDto(42,"Стремлюсь к тому, чтобы мой ребёнок был обеспечен лучше, чем другие дети.", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.ypl),
                TestDto(43,"Если побыть в обществе моего сына (дочери), можно сильно устать.", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.ymin),
                TestDto(44,"Мне часто приходилось давать моему сыну (дочери) трудные для его (ее) возраста поручения.", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.tpl),
                TestDto(45,"Мой ребёнок никогда не убирает за собой игрушки", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.tmin),
                TestDto(46,"Главное, чему родители могут научить своих детей – это слушаться.", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.zpl),
                TestDto(47,"Мой ребёнок сам решает, сколько, чего и когда ему есть.", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.zmin),
                TestDto(48,"Чем строже родители к ребенку, тем лучше для него.", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.cpl),
                TestDto(49,"По характеру я – мягкий человек.", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.smin),
                TestDto(50,"Если моему сыну (дочери) что-то от меня нужно, он(а) старается выбрать момент, когда я в хорошем настроении.", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.n),
                TestDto(51,"Когда я думаю о том, что когда-нибудь мой сын (дочь) вырастет и я буду ему (ей) не нужна, у меня портится настроение.", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.rrc),
                TestDto(52,"Чем старше дети, тем труднее иметь с ними дело.", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.pdk),
                TestDto(53,"Чаще всего упрямство ребёнка бывает вызвано тем, что родители не умеют к нему подойти.", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.vn),
                TestDto(54,"Я постоянно переживаю за здоровье сына (дочери).", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.fy),
                TestDto(55,"Если бы у меня не было детей, мое здоровье было бы гораздо лучше.", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.nrc),
                TestDto(56,"Некоторые очень важные недостатки моего сына (дочери) упорно не исчезают, несмотря на все меры.", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.pnk),
                TestDto(57,"Мой сын (дочь) недолюбливает моего мужа.", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.vn),
                TestDto(58,"Мужчина хуже умеет понимать чувства другого человека, чем женщина.", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.pzk),
                TestDto(59,"Женщина хуже умеет понимать чувства другого человека, чем мужчина.", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.pmk),
                TestDto(60,"Ради моего сына (дочери) мне от многого в жизни пришлось и приходится отказываться.", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.gpl),
                TestDto(61,"Родители, которые слишком много суетятся вокруг своих детей, вызывают у меня раздражение.", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.gmin),
                TestDto(62,"Я трачу на моего сына (дочь) значительно больше денег, чем на себя.", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.ypl),
                TestDto(63,"Не люблю, когда сын (дочь) что-то просит. Сам(а) лучше знаю, чего ему (ей) больше надо.", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.ymin),
                TestDto(64,"У моего сына (дочери) более трудное детство, чем у большинства его (ее) товарищей.", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.tpl),
                TestDto(65,"Дома мой сын (дочь) делает только то, что ему (ей), хочется, а не то, что надо.", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.tmin),
                TestDto(66,"Дети должны уважать родителей больше, чем всех других людей.", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.zpl),
                TestDto(67,"Если мой ребёнок не спит, когда ему положено, я не настаиваю.", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.zmin),
                TestDto(68,"Я строже отношусь к своему сыну (дочери), чем другие родители к своим детям.", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.cpl),
                TestDto(69,"От наказаний мало проку.", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.smin),
                TestDto(70,"Члены нашей семьи неодинаково строги с сыном (дочерью). Одни балуют, другие, наоборот, - очень строги.", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.n),
                TestDto(71,"Мне бы хотелось, чтобы мой сын (дочь) не любил никого, кроме меня.", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.rrc),
                TestDto(72,"Мне нравятся маленькие дети, поэтому не хотел(а) бы, чтобы он(а) слишком быстро взрослел(а).", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.pdk),
                TestDto(73,"Часто я не знаю, как правильно поступить с моим сыном (дочерью).", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.vn),
                TestDto(74,"В связи с плохим здоровьем сына (дочери) нам приходится ему (ей) многое позволять.", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.fy),
                TestDto(75,"Воспитание детей – тяжёлый и неблагодарный труд. Им отдаёшь всё, а взамен не получаешь ничего.", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.nrc),
                TestDto(76,"С моим сыном (дочерью) мало помогает доброе слово. Единственное, что на него действует – это постоянные строгие наказания.", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.pnk),
                TestDto(77,"Мой муж (жена) пытается настроить сына (дочь) против меня.", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.vk),
                TestDto(78,"Мужчины чаще, чем женщины, действуют безрассудно, не обдумав последствий.", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.pzk),
                TestDto(79,"Женщины чаще, чем мужчины, действуют безрассудно, не обдумав последствий.", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.pzk),
                TestDto(80,"Я всё время думаю о моём сыне (дочери), о его делах, здоровье и т.д.", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.gpl),
                TestDto(81,"Так повелось, что о ребёнке я вспоминаю, если он что-нибудь натворил или с ним что-нибудь случилось.", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.gmin),
                TestDto(82,"Мой сын (дочь) умеет добиться от меня того, чего он хочет.", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.ypl),
                TestDto(83,"Мне больше нравятся тихие и спокойные дети.", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.ymin),
                TestDto(84,"Стараюсь как можно раньше приучить ребёнка помогать по дому.", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.tpl),
                TestDto(85,"У моего сына (дочери) мало обязанностей по дому.", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.tmin),
                TestDto(86,"Даже если дети уверены, что родители неправы, они должны делать так, как говорят родители.", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.zpl),
                TestDto(87,"В нашей семье так принято, что ребёнок делает, что хочет.", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.zmin),
                TestDto(88,"Бывают случаи, когда лучшее наказание – ремень.", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.cpl),
                TestDto(89,"Многие недостатки в поведении моего ребёнка пройдут сами собой с возрастом.", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.smin),
                TestDto(90,"Когда наш сын (дочь) что-то натворит, мы боремся за него (ее). Если всё тихо, мы опять оставляем его (ее) в покое.", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.n),
                TestDto(91,"Если бы мой сын не был бы моим сыном, а я была бы помоложе, то наверняка в него влюбилась бы.", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.rrc),
                TestDto(92,"Мне интереснее говорить с маленькими детьми, чем с большими.", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.pdk),
                TestDto(93,"В недостатках моего сына (дочери) виноват(а) я сам(а), потому что не умел(а) его (ее) воспитывать.", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.vn),
                TestDto(94,"Только благодаря нашим огромным усилиям сын (дочь) остался жить.", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.fy),
                TestDto(95,"Нередко завидую тем, кто живет без детей.", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.nrc),
                TestDto(96,"Если предоставить моему сыну (дочери) свободу, он(а) немедленно использует это во вред себе или окружающим.", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.pnk),
                TestDto(97,"Нередко бывает, что если я говорю сыну (дочери) одно, то муж (жена) специально говорит наоборот.", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.vk),
                TestDto(98,"Мужчины чаще, чем женщины, думают только о себе.", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.pzk),
                TestDto(99,"Женщины чаще, чем мужчины, думают только о себе.", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.pmk),
                TestDto(100,"Я трачу на сына (дочь) больше сил и времени, чем на себя.", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.gpl),
                TestDto(101,"Я довольно мало знаю о делах сына (дочери).", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.gmin),
                TestDto(102,"Желание моего сына (дочери) - для меня закон.", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.ypl),
                TestDto(103,"Мой сын очень любит спать со мной.", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.rrc),
                TestDto(104,"У моего сына (дочери) плохой желудок.", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.fy),
                TestDto(105,"Родители нужны ребёнку лишь пока он не вырос. Потом он всё реже вспоминает о них.", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.nrc),
                TestDto(106,"Ради сына (дочери) я пошел бы на любую жертву.", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.gpl),
                TestDto(107,"Моему сыну (дочери) нужно уделять значительно больше времени, чем я могу.", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.gmin),
                TestDto(108,"Мой сын (дочь) умеет быть таким милым, что я ему (ей) всё прощаю.", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.ypl),
                TestDto(109,"Мне бы хотелось, чтобы сын женился попозже, после 30 лет.", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.rrc),
                TestDto(110,"Руки и ноги моего сына (дочери) часто бывают очень холодными.", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.fy),
                TestDto(111,"Большинство детей – маленькие эгоисты. Они совсем не думают о здоровье и чувствах своих родителей.", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.nrc),
                TestDto(112,"Если не отдавать моему сыну (дочери) все время и силы, то все может плохо кончиться.", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.gpl),
                TestDto(113,"Когда всё благополучно, я меньше всего интересуюсь делами сына (дочери).", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.gmin),
                TestDto(114,"Мне очень трудно сказать своему ребенку \"Нет\".", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.ypl),
                TestDto(115,"Меня огорчает, что мой сын все менее нуждается во мне.", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.rrc),
                TestDto(116,"Здоровье моего сына (дочери) хуже, чем у большинства других детей.", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.fy),
                TestDto(117,"Многие дети испытывают слишком мало благодарности по отношению к родителям.", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.nrc),
                TestDto(118,"Мой сын (дочь) не может обходиться без моей постоянной помощи.", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.gpl),
                TestDto(119,"Большую часть своего времени сын (дочь) проводит вне дома – в яслях, детском саду, у родственников.", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.gmin),
                TestDto(120,"У моего сына (дочери) вполне хватает времени на игры и развлечени.", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.ypl),
                TestDto(121,"Кроме моего сына мне больше никто на свете не нужен.", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.rrc),
                TestDto(122,"У моего сына (дочери) прерывистый и беспокойный сон.", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.fy),
                TestDto(123,"Нередко думаю, что я слишком рано женился (вышла замуж).", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.nrc),
                TestDto(124,"Всё, чему научился мой ребенок к настоящему времени, произошло только благодаря моей постоянной помощи.", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.gpl),
                TestDto(125,"Делами сына (дочери) в основном занимается мой муж (жена).", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.gmin),
                TestDto(126,"Я не могу вспомнить, когда в последний раз отказал(а) своему ребенку в покупке какой-нибудь вещи (мороженное, конфеты, \"пепси\" и т.д.).", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.ypl),
                TestDto(127,"Мой сын говорил мне – вырасту, женюсь на тебе, мама.", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.rrc),
                TestDto(128,"Мой сын (дочь) часто болеет.", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.fy),
                TestDto(129,"Семья не помогает, а осложняет мою жизнь.", mutableListOf("+", "-", "не знаю"), mutableListOf(1,0,0), SppBallType.nrc)

                )
    }

}
