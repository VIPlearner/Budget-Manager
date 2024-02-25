package com.viplearner.budgetmanager.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.viplearner.budgetmanager.R
import com.viplearner.budgetmanager.ui.theme.base80
import com.viplearner.budgetmanager.ui.theme.secondary

@Composable
fun BudgetCategoryItem(
    modifier: Modifier = Modifier,
    categoryName: String = "Category Name",
    totalBudget: Float,
    remainingBudget: Float,
    spentBudget: Float,
    icon: ImageVector,
    color: Color,
    isEnabled: Boolean
) {
    Box{
        Column(modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp)
        ) {
            Row(Modifier.padding(top = 10.dp, start = 15.dp, end = 15.dp, bottom = 5.dp), verticalAlignment = Alignment.CenterVertically) {
                Card(
                    modifier = Modifier.size(50.dp),
                    colors = CardDefaults.cardColors(containerColor = color),
                    shape = CircleShape,

                    ) {
                    Icon(
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxSize(),
                        imageVector = icon,
                        contentDescription = "Category Icon",
                        tint = Color.White
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.SpaceEvenly,
                ) {
                    Text(
                        text = categoryName,
                        fontWeight = FontWeight.SemiBold,
                        style = MaterialTheme.typography.titleMedium.copy(fontSize = 18.sp),
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    val annotatedString = buildAnnotatedString {
                        val style =
                            MaterialTheme.typography.titleMedium.copy( fontWeight = FontWeight.SemiBold)
                        pushStyle(style.copy(color = base80).toSpanStyle())
                        append("spent ")
                        pushStyle(style.copy(color = secondary).toSpanStyle())
                        append("\$$spentBudget")
                        pushStyle(style.copy(color = base80).toSpanStyle())
                        append(" of \$$totalBudget")
                    }
                    Text(
                        text = annotatedString,
                    )
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(0.dp)
                ) {
                    Text(
                        text = "\$${String.format("%.0f",remainingBudget)}",
                        fontWeight = FontWeight.SemiBold,
                        color = secondary,
                        style = MaterialTheme.typography.headlineSmall
                    )
                    Text(
                        text = "left",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.SemiBold
                    )
                }

            }
            ProgressBar(
                modifier = Modifier.fillMaxWidth(),
                currentProgress = spentBudget,
                maxProgress = totalBudget,
                color = color
            )
        }
        if(isEnabled){
            Box(modifier.background(base80.copy(alpha = 0.5f)))
        }
    }

}

@Preview
@Composable
fun BudgetCategoryItemPreview() {
    BudgetCategoryItem(
        categoryName = "Food",
        totalBudget = 1000f,
        remainingBudget = 500f,
        spentBudget = 500f,
        icon = ImageVector.vectorResource(R.drawable.food),
        color = Color.Blue,
        isEnabled = false
    )
}

