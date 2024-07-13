package com.groot.chartifydemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.groot.chartify.dto.ChartData
import com.groot.chartify.ui.groupedBarGraph.GroupedBarGraph
import com.groot.chartify.ui.theme.Green
import com.groot.chartify.ui.theme.LightBlue
import com.groot.chartify.ui.theme.Red300
import com.groot.chartify.ui.theme.Yellow
import com.groot.chartifydemo.ui.theme.ChartifyDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChartifyDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Column {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )

        GroupedBarGraph(
            modifier = Modifier
                .fillMaxWidth()
                .padding(25.dp)
                .height(200.dp),
            inputs = clusteredBarChartInputs()
        )
    }
}


fun clusteredBarChartInputs(): HashMap<String, List<ChartData>> {
    val hashMap = HashMap<String, List<ChartData>>()

    val colorPresent = getAttendanceStatusColor(AttendanceStatus.P.name)
    val colorLeave = getAttendanceStatusColor(AttendanceStatus.L.name)
    val colorAbsent = getAttendanceStatusColor(AttendanceStatus.A.name)

    val list = ArrayList<ChartData>()

    list.add(ChartData(64.0f, colorPresent))
    list.add(ChartData(21.0f, colorLeave))
    list.add(ChartData(24.0f, colorAbsent))
    hashMap["Nursery"] = list

    val list1 = ArrayList<ChartData>()

    list1.add(ChartData(67.0f, colorPresent))
    list1.add(ChartData(22.0f, colorLeave))
    list1.add(ChartData(24.0f, colorAbsent))
    hashMap["LKG"] = list1

    val list2 = ArrayList<ChartData>()
    list2.add(ChartData(68.0f, colorPresent))
    list2.add(ChartData(21.0f, colorLeave))
    list2.add(ChartData(22.0f, colorAbsent))
    hashMap["UKG"] = list2


    return hashMap
}

fun getAttendanceStatusColor(status: String): Color {
    return when (status) {
        AttendanceStatus.A.toString() -> Red300
        AttendanceStatus.L.toString() -> Yellow
        AttendanceStatus.P.toString() -> Green
        else -> LightBlue
    }
}

enum class AttendanceStatus {
    A, P, L, E
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ChartifyDemoTheme {
        Greeting("Android")
    }
}