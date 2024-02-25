package com.viplearner.budgetmanager.ui.component

import androidx.annotation.DrawableRes
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.viplearner.budgetmanager.R
import com.viplearner.budgetmanager.ui.theme.base80
import com.viplearner.budgetmanager.ui.theme.primary

val bottomBarItems = listOf(
    BottomBarItem(
        icon = R.drawable.home_icon,
        label = "Home"
    ),
    BottomBarItem(
        icon = R.drawable.trend_icon,
        label = "Trends"
    ),
    BottomBarItem(
        icon = R.drawable.transaction_icon,
        label = "Transactions"
    ),
    BottomBarItem(
        icon = R.drawable.community_icon,
        label = "Community"
    ),
    BottomBarItem(
        icon = R.drawable.profile_icon,
        label = "Profile"
    )
)

data class BottomBarItem(
    val icon: Int,
    val label: String
)

@Composable
fun BottomBarItem(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int,
    label: String,
    isActive: Boolean,
    onClick: () -> Unit
){
    Column(
        modifier = modifier.height(60.dp).clickable(
            interactionSource = MutableInteractionSource(),
            indication = null,
            enabled = !isActive,
        ) { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = icon),
            contentDescription = label,
            tint = if (isActive) primary else base80
        )
        Text(
            text = label,
            color = if (isActive) primary else base80,
            maxLines = 1,
            fontWeight = FontWeight.Medium,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun BottomBar(){
    var currentBottomItem by remember {
        mutableStateOf(0)
    }
    Column {
        BoxWithConstraints(modifier = Modifier
            .fillMaxWidth()
            .height(2.dp)){
            // Bottom bar indicator

            val animatableOffset = remember { Animatable(
                currentBottomItem.times((maxWidth / bottomBarItems.size).value)
            ) }
            LaunchedEffect(currentBottomItem) {
                animatableOffset.animateTo(currentBottomItem.times((maxWidth / bottomBarItems.size).value), animationSpec = tween(durationMillis = 500))
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth(1f / bottomBarItems.size)
                    .height(2.dp)
                    .offset(x = animatableOffset.value.dp)
                    .padding(horizontal = 12.dp)
                    .background(primary)
            )

        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            bottomBarItems.forEachIndexed { index, (icon, label) ->
                BottomBarItem(
                    modifier = Modifier.weight(1f),
                    icon = icon,
                    label = label,
                    isActive = index == currentBottomItem,
                    onClick = { currentBottomItem = index }
                )
            }
        }
    }
}

@Preview
@Composable
fun BottomBarPreview(){
    BottomBar()
}