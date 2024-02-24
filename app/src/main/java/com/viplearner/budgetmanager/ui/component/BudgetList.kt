package com.viplearner.budgetmanager.ui.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.viplearner.budgetmanager.R
import com.viplearner.budgetmanager.ui.theme.colorItemList

@Composable
fun BudgetList(
    modifier: Modifier = Modifier,
    budgetCategoryList: List<BudgetCategory>
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        items(budgetCategoryList) { budgetCategory ->
            BudgetCategoryItem(
                categoryName = budgetCategory.categoryName,
                totalBudget = budgetCategory.totalBudget,
                remainingBudget = budgetCategory.remainingBudget,
                spentBudget = budgetCategory.spentBudget,
                icon = ImageVector.vectorResource(id = budgetCategory.icon),
                color = budgetCategory.color,
                isEnabled = budgetCategory.isEnabled
            )
        }
    }
}

@Preview
@Composable
fun BudgetListPreview() {
    Box(modifier = Modifier.padding(30.dp)){
        BudgetList(
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
                    color = colorItemList[2]
                )
            )
        )
    }
}

data class BudgetCategory(
    val categoryName: String,
    val totalBudget: Float,
    val remainingBudget: Float,
    val spentBudget: Float,
    @DrawableRes val icon: Int,
    val color: Color,
    val isEnabled: Boolean = true
)