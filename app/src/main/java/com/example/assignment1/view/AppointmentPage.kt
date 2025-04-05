package com.example.assignment1.view

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import android.graphics.Paint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.tween
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.Path
import java.time.YearMonth
import java.util.Calendar

@RequiresApi(Build.VERSION_CODES.O) // Call API
private fun createCalendarList(month: Int, year: Int): List<CalendarInput> {
    val daysInMonth = YearMonth.of(year, month).lengthOfMonth()
    val calendarInputs = mutableListOf<CalendarInput>()
    for (i in 1..daysInMonth) {
        calendarInputs.add(
            CalendarInput(
                i,
                toDos = listOf(
                    "Day $i:",
                    "Task 1 Bla Bla Bla",
                    "Task 2 Test Test Tes"
                )
            )
        )
    }
    return calendarInputs
}

private const val CALENDAR_ROWS = 5
private const val CALENDAR_COLUMNS = 7

@RequiresApi(Build.VERSION_CODES.O) // Request API for real time date and time of a particular year
@Composable
fun AppointmentPage(navController: NavHostController) {
    val calendar = Calendar.getInstance()
    val currentYear = calendar.get(Calendar.YEAR)
    var selectedMonth by remember { mutableStateOf(calendar.get(Calendar.MONTH) + 1) }

    val calendarInputList by remember(selectedMonth) {
        mutableStateOf(createCalendarList(selectedMonth, currentYear))
    }

    var clickedCalendarElem by remember { mutableStateOf<CalendarInput?>(null) }

    Column(
        modifier = Modifier.fillMaxSize().padding(vertical = 60.dp, horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Button(
                onClick = {
                    selectedMonth = if (selectedMonth == 1) 12 else selectedMonth - 1
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFC3B091))
            ) { Text("<", color = Color.Black) }

            Text("${YearMonth.of(currentYear, selectedMonth).month}", fontSize = 24.sp, fontWeight = FontWeight.Bold)

            Button(
                onClick = {
                    selectedMonth = if (selectedMonth == 12) 1 else selectedMonth + 1
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFC3B091))
            ) { Text(">", color = Color.Black) }
        }

        Calendar(
            calendarInput = calendarInputList,
            onDayClick = { day -> clickedCalendarElem = calendarInputList.first { it.day == day } },
            month = "${YearMonth.of(currentYear, selectedMonth).month}",
            modifier = Modifier.padding(10.dp).fillMaxWidth().aspectRatio(1.3f)
        )

        clickedCalendarElem?.toDos?.forEach {
            Text(if (it.contains("Day")) it else "- $it", fontWeight = FontWeight.SemiBold, fontSize = if (it.contains("Day")) 25.sp else 18.sp)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { navController.navigate("addAppointment") }, // Navigate to Add Appointment Page
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFC3B091))
        ) {
            Text("Add Appointment", color = Color.Black)
        }
    }
}


@Composable
fun Calendar(
    modifier: Modifier = Modifier,
    calendarInput: List<CalendarInput>,
    onDayClick: (Int) -> Unit,
    strokeWidth: Float = 15f,
    month: String
) {

    var canvasSize by remember {
        mutableStateOf(Size.Zero)
    }
    var clickAnimationOffset by remember {
        mutableStateOf(Offset.Zero)
    }

    var animationRadius by remember {
        mutableFloatStateOf(0f)
    }

    val scope = rememberCoroutineScope()

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(true) {
                    detectTapGestures(
                        onTap = { offset ->
                            val column =
                                (offset.x / canvasSize.width * CALENDAR_COLUMNS).toInt() + 1
                            val row = (offset.y / canvasSize.height * CALENDAR_ROWS).toInt() + 1
                            val day = column + (row - 1) * CALENDAR_COLUMNS
                            if (day <= calendarInput.size) {
                                onDayClick(day)
                                clickAnimationOffset = offset
                                scope.launch {
                                    animate(0f, 225f, animationSpec = tween(300)) { value, _ ->
                                        animationRadius = value
                                    }
                                }
                            }

                        }
                    )
                }
        ) {
            val canvasHeight = size.height
            val canvasWidth = size.width
            canvasSize = Size(canvasWidth, canvasHeight)
            val ySteps = canvasHeight / CALENDAR_ROWS
            val xSteps = canvasWidth / CALENDAR_COLUMNS

            val column = (clickAnimationOffset.x / canvasSize.width * CALENDAR_COLUMNS).toInt() + 1
            val row = (clickAnimationOffset.y / canvasSize.height * CALENDAR_ROWS).toInt() + 1

            val path = Path().apply {
                moveTo((column - 1) * xSteps, (row - 1) * ySteps)
                lineTo(column * xSteps, (row - 1) * ySteps)
                lineTo(column * xSteps, row * ySteps)
                lineTo((column - 1) * xSteps, row * ySteps)
                close()
            }

            clipPath(path) {
                drawCircle(
                    brush = Brush.radialGradient(
                        listOf(Color(0xFFC3B091).copy(0.8f), Color(0xFFC3B091).copy(0.2f)),
                        center = clickAnimationOffset,
                        radius = animationRadius + 0.1f
                    ),
                    radius = animationRadius + 0.1f,
                    center = clickAnimationOffset
                )
            }

            drawRoundRect(
                Color(0xFFC3B091),
                cornerRadius = CornerRadius(25f, 25f),
                style = Stroke(
                    width = strokeWidth
                )
            )

            for (i in 1 until CALENDAR_ROWS) {
                drawLine(
                    color = Color(0xFFC3B091),
                    start = Offset(0f, ySteps * i),
                    end = Offset(canvasWidth, ySteps * i),
                    strokeWidth = strokeWidth
                )
            }
            for (i in 1 until CALENDAR_COLUMNS) {
                drawLine(
                    color = Color(0xFFC3B091),
                    start = Offset(xSteps * i, 0f),
                    end = Offset(xSteps * i, canvasHeight),
                    strokeWidth = strokeWidth
                )
            }
            val textHeight = 17.dp.toPx()
            for (i in calendarInput.indices) {
                val textPositionX = xSteps * (i % CALENDAR_COLUMNS) + strokeWidth
                val textPositionY = (i / CALENDAR_COLUMNS) * ySteps + textHeight + strokeWidth / 2
                drawContext.canvas.nativeCanvas.apply {
                    drawText(
                        "${i + 1}",
                        textPositionX,
                        textPositionY,
                        Paint().apply {
                            textSize = textHeight
                            isFakeBoldText = true
                        }
                    )
                }
            }
        }
    }

}

data class CalendarInput(
    val day: Int,
    val toDos: List<String> = emptyList()
)