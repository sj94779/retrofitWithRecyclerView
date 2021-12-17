package com.example.recycleviewwithretrofit

object ColorPicker {
    val colors =
        arrayOf("#3EB9DF", "#3685BC", "#D36280", "#E44F55", "#FA8056","#818BCA","#7D659F")
    var colorIndex = 1
    fun getColors(): String{
        return colors[colorIndex++ % colors.size]
    }
}