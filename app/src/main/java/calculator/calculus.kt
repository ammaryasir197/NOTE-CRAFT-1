package calculator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.tan
import kotlin.math.sqrt

@Preview(showBackground = true , showSystemUi = true)
@Composable
fun Calculations () {
    var displayText by remember { mutableStateOf("0") }
    var operator by remember { mutableStateOf("") }
    var firstValue by remember { mutableStateOf(0.0) }
    var expression by remember { mutableStateOf("") }
    var pendingFunction by remember { mutableStateOf("") }
    val buttons = listOf(
        "AC", "DEL", "√", "π",
        "sin", "cos", "tan", "/",
        "7", "8", "9", "*",
        "4", "5", "6", "-",
        "1", "2", "3", "+",
        "0", ".", "="
    )
    Column(
        modifier = Modifier.fillMaxSize()
            .background(color = Color.Black),
        verticalArrangement = Arrangement.Bottom

    ) {
        Text(
            text = expression,
            fontSize = 24.sp,
            color = Color.Gray
        )
        Text(
            text = displayText,
            fontSize = 68.sp,
            color = Color.White,
            modifier = Modifier.fillMaxWidth().padding(bottom = 140.dp),
            textAlign = TextAlign.End
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(buttons) { label ->
                Button(
                    onClick = {when (label) {
                        "AC" -> {
                            displayText = "0"
                            operator = ""
                            firstValue = 0.0
                            pendingFunction = ""
                            expression = ""
                        }
                        "DEL" -> {
                            displayText = if (displayText.length > 1) displayText.dropLast(1) else "0"
                        }
                        "+", "-", "*", "/" -> {
                            firstValue = displayText.toDouble()
                            operator = label
                            displayText =  displayText + label
                        }
                        "sin", "cos", "tan", "√" -> {
                            pendingFunction = label
                            displayText = if (displayText == "0") label else displayText + label
                        }
                        "π" -> {
                            pendingFunction = "π"
                            displayText = if (displayText == "0") label else displayText + label
                        }
                        "=" -> {
                            expression = displayText

                            if (pendingFunction.isNotEmpty()) {
                                val numberPart = displayText.removePrefix(pendingFunction)
                                val value = if (numberPart.isEmpty()) 0.0 else numberPart.toDouble()
                                val result = when (pendingFunction) {
                                    "sin" -> sin(Math.toRadians(value))
                                    "cos" -> cos(Math.toRadians(value))
                                    "tan" -> tan(Math.toRadians(value))
                                    "√" -> sqrt(value)
                                    "π" -> if (value == 0.0) Math.PI else value * Math.PI
                                    else -> value
                                }
                                displayText = result.toString()
                                pendingFunction = ""
                            } else {
                                val secondValue = displayText.substringAfter(operator).toDouble()

                                if (operator == "/" && secondValue == 0.0) {
                                    displayText = "Error"
                                } else {
                                    val result = when (operator) {
                                        "+" -> firstValue + secondValue
                                        "-" -> firstValue - secondValue
                                        "*" -> firstValue * secondValue
                                        "/" -> firstValue / secondValue
                                        else -> secondValue
                                    }
                                    displayText = result.toString()
                                }
                            }
                            operator = ""
                        }
                        "." -> {
                            val currentNumber = if (operator.isNotEmpty()) displayText.substringAfterLast(operator) else displayText
                            if (!currentNumber.contains(".")) {
                                displayText = displayText + "."
                            }
                        }
                        else -> {
                            displayText = if (displayText == "0") label else displayText + label
                        }
                    } },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(65.dp),

                    shape = RoundedCornerShape(18.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White.copy(alpha = 0.8f),
                        contentColor = Color.Black

                    )
                ) {
                    Text(text = label, fontSize = 25.sp,
                        color = Color.Black)
                }
            }
        }

    }
}