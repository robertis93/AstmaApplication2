package space.robert.astmaapplication2


fun convertDayOfMounth(day: Int): String {
    val monday = "Понедельник"
    val tuesday = "Вторник"
    val wednesday = "Среда"
    val thursday = "Четверг"
    val friday = "Пятница"
    val saturday = "Суббота"
    val sunday = "Воскресенье"

    if (day == 2)
        return monday

    if (day == 3)
        return tuesday

    if (day == 4)
        return wednesday

    if (day == 5)
        return thursday

    if (day == 6)
        return friday

    return if (day == 7) {
        saturday

    }
    else sunday

}


fun convertMounth(mounth: Int): String {
    val jan = "Январь"
    val feb = "Февраль"
    val march = "Март"
    val aprel = "Апрель"
    val may = "Май"
    val june = "Июнь"
    val july = "Июль"
    val august = "Август"
    val sept = "Сентябрь"
    val oct = "Октябрь"
    val nov = "Ноябрь"
    val dec = "Декабрь"

    if (mounth == 0)
        return jan

    if (mounth == 1)
        return feb

    if (mounth == 2)
        return march

    if (mounth == 3)
        return aprel

    if (mounth == 4)
        return may

    if (mounth == 5)
        return june

    if (mounth == 6)
        return july

    if (mounth == 7)
        return august

    if (mounth == 8)
        return sept

    if (mounth == 9)
        return oct





    return if (mounth == 10) {
        nov

    }
    else dec

}

