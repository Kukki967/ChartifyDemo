package com.groot.chartify.helper

import androidx.compose.ui.unit.dp

/* *******************************************************************  ******************************************************************* */
val defaultMaxHeight = 200.dp

fun List<Float>.toPercent(): List<Float> {
    return this.map { item ->
        item * 100 / this.sum()
    }
}



