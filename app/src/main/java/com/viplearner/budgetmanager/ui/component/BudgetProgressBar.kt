package com.viplearner.budgetmanager.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.viplearner.budgetmanager.R
import com.viplearner.budgetmanager.ui.theme.base40
import com.viplearner.budgetmanager.ui.theme.colorItemList

@Composable
fun BudgetProgressBar(
    budgetCategoryList: List<BudgetCategory>,
    modifier: Modifier = Modifier
) {
    BoxWithConstraints(
        modifier = modifier
            .background(color = base40, shape = RoundedCornerShape(13.dp))
            .clip(RoundedCornerShape(15.dp))
            .height(45.dp)
            .fillMaxWidth()
    ){
        val totalBudget = budgetCategoryList.sumOf {
            it.totalBudget.toDouble()
        }
        val maxWidth = maxWidth
        Row{
            budgetCategoryList.forEachIndexed { index, budgetCategory ->
                val spentBudget = budgetCategory.spentBudget
                val color = budgetCategory.color
                val fraction = (spentBudget / totalBudget).toFloat()
                val width = maxWidth.value * fraction
                val shape = if (index == budgetCategoryList.lastIndex) {
                    RoundedCornerShape(topEnd = 13.dp, bottomEnd = 13.dp)
                } else {
                    RoundedCornerShape(0.dp)
                }
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(width.dp)
                        .background(color, shape)
                        .clip(shape)
                )
            }
        }

    }
}

@Preview
@Composable
fun BudgetProgressBarPreview() {
    BudgetProgressBar(
        modifier = Modifier.padding(40.dp),
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
                categoryName = "Transport",
                totalBudget = 1000f,
                remainingBudget = 500f,
                spentBudget = 500f,
                icon = R.drawable.food,
                color = colorItemList[2],
                isEnabled = false
            ),
            BudgetCategory(
                categoryName = "Health",
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
            BudgetCategory(
                categoryName = "Miscellaneous",
                totalBudget = 1000f,
                remainingBudget = 500f,
                spentBudget = 500f,
                icon = R.drawable.food,
                color = colorItemList[5]
            )
        )
    )
}