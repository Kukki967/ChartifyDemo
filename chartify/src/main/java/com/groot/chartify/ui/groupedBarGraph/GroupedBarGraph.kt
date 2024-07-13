package com.groot.chartify.ui.groupedBarGraph

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.groot.chartify.dto.ChartData
import com.groot.chartify.helper.defaultMaxHeight
import com.groot.chartify.ui.theme.PurPle
import com.groot.chartify.ui.theme.SilverChalice


@Composable
fun GroupedBarGraph(
    modifier: Modifier = Modifier,
    inputs: Map<String, List<ChartData>>,
    maxHeight: Dp = defaultMaxHeight
) {
    assert(inputs.isNotEmpty()) { "Input values are empty" }

    val density = LocalDensity.current
    val strokeWidth = with(density) { 1.dp.toPx() }

    Row(
        modifier = modifier.then(
            Modifier
                .fillMaxWidth()
                .height(maxHeight)
                .drawBehind {
                    // draw X-Axis
                    drawLine(
                        color = PurPle,
                        start = Offset(0f, size.height),
                        end = Offset(size.width, size.height),
                        strokeWidth = strokeWidth
                    )
                    // draw Y-Axis
                    drawLine(
                        color = SilverChalice,
                        start = Offset(0f, 0f),
                        end = Offset(0f, size.height),
                        strokeWidth = strokeWidth
                    )
                }
        ),
        verticalAlignment = Alignment.Bottom
    ) {
        inputs.forEach { (key, item) ->
            Row(modifier = Modifier.weight(1f), verticalAlignment = Alignment.Bottom) {
                item.forEachIndexed { index, input ->
                    val itemHeight = remember(input) { input.value * maxHeight.value / 100 }
                    Spacer(
                        modifier = Modifier
                            .height(itemHeight.dp)
                            .width(5.dp)
                            .background(input.color)
                    )
                }
            }
        }

    }
}
