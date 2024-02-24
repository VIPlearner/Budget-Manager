package com.viplearner.budgetmanager.ui.component

import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.viplearner.budgetmanager.ui.theme.base60

@Composable
fun ProgressBar(
    modifier: Modifier = Modifier,
    currentProgress: Float,
    maxProgress: Float,
    height: Dp = 4.dp,
    color: Color,
) {
    BoxWithConstraints(
        modifier = modifier.fillMaxWidth()
            .height(height)
            .border(BorderStroke(0.dp, Color.Transparent))
            .background(base60)
            .shadow(elevation = 4.dp, shape = RectangleShape)

    ) {
        val fraction = currentProgress / maxProgress
        val animatableFraction = remember { androidx.compose.animation.core.Animatable(fraction) }
        LaunchedEffect(currentProgress) {
            animatableFraction.animateTo(fraction, animationSpec = tween(durationMillis = 5000))
        }
        Box(modifier = Modifier.fillMaxHeight().fillMaxWidth(animatableFraction.value).background(color))
    }
}

@Preview
@Composable
fun ProgressBarPreview() {
    ProgressBar(
        currentProgress = 0.5f,
        maxProgress = 1f,
        color = Color.Blue
    )
}