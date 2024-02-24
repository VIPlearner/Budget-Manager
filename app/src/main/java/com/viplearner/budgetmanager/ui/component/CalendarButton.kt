package com.viplearner.budgetmanager.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties
import com.viplearner.budgetmanager.ui.theme.base0
import com.viplearner.budgetmanager.ui.theme.secondary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarButton(
    modifier: Modifier = Modifier,
    monthYearText: String,
    currentMonth: Int,
    currentYear: Int,
    onMonthPicked: (Int, Int) -> Unit
) {
    var isExpanded by remember {
        mutableStateOf(false)
    }

    BoxWithConstraints(modifier.fillMaxWidth()){
        val maxWidth = maxWidth
        Card(
            modifier = Modifier.align(Alignment.Center),
            colors = CardDefaults.cardColors(
                containerColor = secondary.copy(alpha = 0.15f),
                contentColor = secondary
            ),
            onClick = {
                isExpanded = !isExpanded
            },
            shape = RoundedCornerShape(4.dp)
        ) {
            Row(
                modifier = Modifier
                    .padding(2.dp)
                    .padding(start = 2.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = monthYearText,
                    fontWeight = FontWeight.SemiBold
                )
                Icon(
                    imageVector = Icons.Filled.ArrowDropDown,
                    contentDescription = "calendar button"
                )
            }
        }
        DropdownMenu(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .background(base0),
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false },
            properties = PopupProperties(
                focusable = true,
            ),
            offset = DpOffset(0.dp, 0.dp)
        ) {
            MonthPicker(
                modifier = Modifier.background(base0),
                width = maxWidth,
                currentMonth = currentMonth,
                currentYear = currentYear,
                confirmButtonClicked = { month, year ->
                    isExpanded = false
                    onMonthPicked(month, year)
                }
            ) {
                isExpanded = false
            }
        }
    }

}

@Preview
@Composable
fun CalendarButtonPreview() {
    CalendarButton(
        modifier = Modifier,
        monthYearText = "June 2021",
        currentMonth = 6,
        currentYear = 2021
    ) {month, year ->
    }
}

