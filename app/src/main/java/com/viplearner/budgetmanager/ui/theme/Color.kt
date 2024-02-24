package com.viplearner.budgetmanager.ui.theme

import androidx.compose.ui.graphics.Color
import kotlin.random.Random

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val secondary = Color(0xFF53BD71)
val primary = Color(0xFF5157BF)
val primaryVariant = Color(0xFF6970C9)

val base80 = Color(0xFF707070)
val base60 = Color(0xFFA0A0A0)
val base40 = Color(0xFFDDDDDD)
val base0 = Color(0xFFFFFFFF)

val colorItemList = listOf(
    Color(0xFF213B80),
    Color(0xFF386BBC),
    Color(0xFFFFB900),
    Color(0xFF46BDC6),
    Color(0xFF7D5260),
    Color(0xFF6650A4),
    Color(0xFF625B71),
    Color(0xFF707070),
    Color(0xFFA0A0A0),
    Color(0xFFFFFFFF)
)

fun getRandomColor(): Color {
    val rnd: Random = Random.Default
    return Color(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256), 255);
}
