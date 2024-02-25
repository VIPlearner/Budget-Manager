package com.viplearner.budgetmanager.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.viplearner.budgetmanager.R
import com.viplearner.budgetmanager.ui.theme.base0
import com.viplearner.budgetmanager.ui.theme.colorItemList

@Composable
fun BudgetCard(
    budgetCategoryList: List<BudgetCategory>,
    totalBudgetStr: String,
    spentBudgetStr: String,
    remainingBudgetStr: String,
    monthYearText: String,
    currentMonth: Int,
    currentYear: Int,
    modifier: Modifier = Modifier,
    onMonthChanged: (Int, Int) -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 13.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = base0),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
    ) {

        Column(
            modifier = Modifier.padding(top = 15.dp, bottom = 25.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            CalendarButton(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                monthYearText = monthYearText,
                currentMonth = currentMonth,
                currentYear = currentYear
            ) { month, year ->
                onMonthChanged(month, year)
            }
            TotalBudgetTextView(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth(),
                totalBudget = totalBudgetStr,
                spentBudget = spentBudgetStr,
                remainingBudget = remainingBudgetStr
            )
            BudgetProgressBar(
                modifier = Modifier.padding(horizontal = 20.dp),
                budgetCategoryList = budgetCategoryList
            )
            BudgetList(
                budgetCategoryList = budgetCategoryList
            )
        }
    }
}

@Preview
@Composable
fun BudgetCardPreview() {
    BudgetCard(
        budgetCategoryList = listOf(
            BudgetCategory(
                categoryName = "Groceries",
                totalBudget = 1000f,
                remainingBudget = 500f,
                spentBudget = 500f,
                icon = R.drawable.food,
                color = colorItemList[0]
            ),
            BudgetCategory(
                categoryName = "Entertainment",
                totalBudget = 1000f,
                remainingBudget = 500f,
                spentBudget = 500f,
                icon = R.drawable.food,
                color = colorItemList[1]
            ),
            BudgetCategory(
                categoryName = "Health",
                totalBudget = 1000f,
                remainingBudget = 500f,
                spentBudget = 500f,
                icon = R.drawable.food,
                color = colorItemList[2]
            ),
            BudgetCategory(
                categoryName = "Transport",
                totalBudget = 1000f,
                remainingBudget = 500f,
                spentBudget = 500f,
                icon = R.drawable.food,
                color = colorItemList[3]
            ),
            BudgetCategory(
                categoryName = "Education",
                totalBudget = 1000f,
                remainingBudget = 500f,
                spentBudget = 500f,
                icon = R.drawable.food,
                color = colorItemList[4]
            ),
        ),
        totalBudgetStr = "1000",
        spentBudgetStr = "500",
        remainingBudgetStr = "500",
        monthYearText = "September 2021",
        currentMonth = 9,
        currentYear = 2021,
        modifier = Modifier.padding(20.dp),

    ){ month, year ->

    }
}

