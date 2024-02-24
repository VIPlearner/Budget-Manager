package com.viplearner.budgetmanager.ui.component

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.viplearner.budgetmanager.ui.theme.base0
import com.viplearner.budgetmanager.ui.theme.base60
import com.viplearner.budgetmanager.ui.theme.primary
import com.viplearner.budgetmanager.ui.theme.secondary
import java.util.Calendar

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MonthPicker(
    modifier: Modifier = Modifier,
    width: Dp,
    currentMonth: Int,
    currentYear: Int,
    confirmButtonClicked: (Int, Int) -> Unit,
    cancelClicked: () -> Unit
) {

    val months = listOf(
        "January",
        "February",
        "March",
        "April",
        "May",
        "June",
        "July",
        "August",
        "September",
        "October",
        "November",
        "December"
    )

    var month by remember {
        mutableStateOf(months[currentMonth-1])
    }

    var year by remember {
        mutableIntStateOf(currentYear)
    }

    val interactionSource = remember {
        MutableInteractionSource()
    }

    Card(
        modifier = modifier.width(width),
        colors = CardDefaults.cardColors(
            containerColor = base0,
        ),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                modifier = Modifier
                    .size(25.dp)
                    .rotate(90f)
                    .clickable(
                        indication = null,
                        interactionSource = interactionSource,
                        onClick = {
                            year--
                        }
                    ),
                imageVector = Icons.Rounded.KeyboardArrowDown,
                contentDescription = null
            )

            Text(
                modifier = Modifier.padding(horizontal = 20.dp),
                text = year.toString(),
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleLarge,
            )

            Icon(
                modifier = Modifier
                    .size(25.dp)
                    .rotate(-90f)
                    .clickable(
                        indication = null,
                        interactionSource = interactionSource,
                        onClick = {
                            year++
                        }
                    ),
                imageVector = Icons.Rounded.KeyboardArrowDown,
                contentDescription = null
            )

        }


        Card(
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth(),
            elevation = CardDefaults.cardElevation(0.dp),
            colors = CardDefaults.cardColors(
                containerColor = base0,
            ),
        ) {

            FlowRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                maxItemsInEachRow = 4,
                horizontalArrangement = Arrangement.spacedBy(5.dp, Alignment.CenterHorizontally),
            ) {

                months.forEachIndexed {index, it ->
                    val isFutureDate = isFutureDate(index + 1, year)
                    Box(
                        modifier = Modifier
                            .clickable(
                                indication = null,
                                interactionSource = interactionSource,
                                onClick = {
                                    if(!isFutureDate){ month = it }
                                },
                                enabled = !isFutureDate
                            )
                            .background(
                                color = Color.Transparent
                            ),
                        contentAlignment = Alignment.Center
                    ) {

                        val animatedHeight by animateDpAsState(
                            targetValue = if (month == it) 30.dp else 0.dp,
                            animationSpec = tween(
                                durationMillis = 200,
                                easing = LinearOutSlowInEasing
                            ), label = ""
                        )

                        val animatedWidth by animateDpAsState(
                            targetValue = if (month == it) 70.dp else 0.dp,
                            animationSpec = tween(
                                durationMillis = 200,
                                easing = LinearOutSlowInEasing
                            ), label = ""
                        )

                        Box(
                            modifier = Modifier
                                .size(animatedWidth, animatedHeight)
                                .background(
                                    color = if (month == it) secondary.copy(alpha = 0.15f) else Color.Transparent,
                                    shape = RoundedCornerShape(5.dp)
                                )
                        )

                        Box(
                            Modifier
                                .size(70.dp, 30.dp)
                                .background(Color.Transparent),
                        ){
                            Text(
                                text = it,
                                color = if(!isFutureDate)Color.Black else base60,
                                fontWeight = FontWeight.Medium,
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }

                    }
                }

            }

        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {

            TextButton(
                modifier = Modifier.padding(),
                onClick = {
                    cancelClicked()
                },
                colors = ButtonDefaults.outlinedButtonColors(containerColor = Color.Transparent)
            ) {

                Text(
                    text = "Cancel",
                    color = Color.Black,
                    fontWeight = FontWeight.Medium
                )

            }

            TextButton(
                modifier = Modifier.padding(),
                onClick = {
                    confirmButtonClicked(
                        months.indexOf(month) + 1,
                        year
                    )
                },
                colors = ButtonDefaults.outlinedButtonColors(containerColor = Color.Transparent)
            ) {

                Text(
                    text = "OK",
                    color = primary,
                    fontWeight = FontWeight.Medium
                )

            }

        }


    }
}

@Preview
@Composable
fun MonthPickerPreview() {
    MonthPicker(
        width = 300.dp,
        currentMonth = 0,
        currentYear = 2022,
        confirmButtonClicked = { month, year ->

        },
        cancelClicked = {

        }
    )
}

fun isFutureDate(month: Int, year: Int): Boolean {
    val inputDate: Calendar = Calendar.getInstance()
    inputDate.set(Calendar.YEAR, year)
    inputDate.set(Calendar.MONTH, month - 1) // Calendar months are zero-based

    val currentDate: Calendar = Calendar.getInstance()

    // Compare year and month only, ignore day of month, hour, minute, second, and millisecond
    inputDate.set(Calendar.DAY_OF_MONTH, 1)
    inputDate.set(Calendar.HOUR_OF_DAY, 0)
    inputDate.set(Calendar.MINUTE, 0)
    inputDate.set(Calendar.SECOND, 0)
    inputDate.set(Calendar.MILLISECOND, 0)

    currentDate.set(Calendar.DAY_OF_MONTH, 1)
    currentDate.set(Calendar.HOUR_OF_DAY, 0)
    currentDate.set(Calendar.MINUTE, 0)
    currentDate.set(Calendar.SECOND, 0)
    currentDate.set(Calendar.MILLISECOND, 0)

    return inputDate.after(currentDate)
}