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

