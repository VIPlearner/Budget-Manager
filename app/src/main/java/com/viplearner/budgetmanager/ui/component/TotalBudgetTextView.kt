package com.viplearner.budgetmanager.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.viplearner.budgetmanager.ui.theme.base60
import com.viplearner.budgetmanager.ui.theme.base80
import com.viplearner.budgetmanager.ui.theme.secondary

@Composable
fun TotalBudgetTextViewItem(
    text: String,
    value: String,
    color: Color = Color.Black
) {
    Column {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = text,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                color = base80
            )

            Text(
                text = value,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.SemiBold,
                color = color
            )
        }
    }
}

@Composable
fun TotalBudgetTextView(
    modifier: Modifier = Modifier,
    totalBudget: String,
    spentBudget: String,
    remainingBudget: String
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ){
        TotalBudgetTextViewItem(
            text = "Spent",
            value = spentBudget
        )
        Box(modifier = Modifier.height(35.dp).width(1.dp).background(base60))
        TotalBudgetTextViewItem(
            text = "Available",
            value = remainingBudget,
            color = secondary
        )
        Box(modifier = Modifier.height(35.dp).width(1.dp).background(base60))
        TotalBudgetTextViewItem(
            text = "Budget",
            value = totalBudget
        )
    }
}

@Preview
@Composable
fun TotalBudgetTextViewPreview() {
    TotalBudgetTextView(
        modifier = Modifier.fillMaxWidth().padding(35.dp),
        totalBudget = "\$1000",
        spentBudget = "\$500",
        remainingBudget = "\$500"
    )
}