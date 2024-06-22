package com.app.spinwheel.ui.home

data class Sectors(val startDegree: Float, val endDegree: Float, val name: String)

val areas = listOf(
    Sectors(0f, 60f, "Pizza!"),
    Sectors(60f, 120f, "Thai Food!"),
    Sectors(120f, 180f, "Salad!"),
    Sectors(180f, 240f, "Sushi!"),
    Sectors(240f, 300f, "Chinese Food!"),
    Sectors(300f, 360f, "Seafood!")
)

fun getSectorByDegree(degree: Float): Sectors {
    return areas.first { degree >= it.startDegree && degree < it.endDegree }
}