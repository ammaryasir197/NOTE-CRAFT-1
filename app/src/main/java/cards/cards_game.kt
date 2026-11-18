package cards

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.calculator.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// 1) Saari images ki list
val cardImages = listOf(
    R.drawable.ace,
    R.drawable.joker,
    R.drawable.king,
    R.drawable.ten_of_shades,
    R.drawable.eight_of_diamonds,
    R.drawable.nine_of_hearts,
    R.drawable.queen,
    R.drawable.seven_of_clubs
)

data class GameCard(
    val id: Int,
    val imageRes: Int,
    val isFlipped: Boolean = false,
    val isMatched: Boolean = false
)

// Har image 2 baar = 16 cards, shuffle karke
fun createCards(): List<GameCard> =
    (cardImages + cardImages)
        .shuffled()
        .mapIndexed { i, img -> GameCard(i, img) }

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CardsGame() {
    var cards by remember { mutableStateOf(createCards()) }
    var firstIndex by remember { mutableStateOf<Int?>(null) }
    var moves by remember { mutableStateOf(0) }
    var isBusy by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    val isWon = cards.all { it.isMatched }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Score yahan dikhega
        Text("Moves: $moves", style = MaterialTheme.typography.headlineMedium)

        Spacer(Modifier.height(16.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            modifier = Modifier.weight(1f)
        ) {
            itemsIndexed(cards) { index, card ->

                val rotation by animateFloatAsState(
                    targetValue = if (card.isFlipped || card.isMatched) 180f else 0f,
                    animationSpec = tween(400),
                    label = "flip"
                )

                Card(
                    modifier = Modifier
                        .padding(4.dp)
                        .aspectRatio(0.7f)
                        .graphicsLayer {
                            rotationY = rotation
                            cameraDistance = 12f * density
                        }
                        .clickable {
                            if (isBusy || card.isFlipped || card.isMatched) return@clickable

                            // card ko flip karo
                            cards = cards.toMutableList().also {
                                it[index] = card.copy(isFlipped = true)
                            }

                            val first = firstIndex
                            if (first == null) {
                                firstIndex = index
                            } else {
                                moves++
                                firstIndex = null

                                if (cards[first].imageRes == cards[index].imageRes) {
                                    // match ho gaya
                                    cards = cards.toMutableList().also {
                                        it[first] = it[first].copy(isMatched = true)
                                        it[index] = it[index].copy(isMatched = true)
                                    }
                                } else {
                                    // match nahi, 1 sec baad wapas ulte
                                    isBusy = true
                                    scope.launch {
                                        delay(1000)
                                        cards = cards.toMutableList().also {
                                            it[first] = it[first].copy(isFlipped = false)
                                            it[index] = it[index].copy(isFlipped = false)
                                        }
                                        isBusy = false
                                    }
                                }
                            }
                        }
                ) {
                    if (rotation <= 90f) {
                        // Card ka ulta side
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(MaterialTheme.shapes.medium)
                                .background(Color(0xFF3F51B5)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("?", color = Color.White, style = MaterialTheme.typography.headlineLarge)
                        }
                    } else {
                        // Card ka seedha side (image)
                        Image(
                            painter = painterResource(card.imageRes),
                            contentDescription = null,
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .fillMaxSize()
                                .graphicsLayer { rotationY = 180f }
                        )
                    }
                }
            }
        }

        if (isWon) {
            Text(
                "🎉 You won in $moves moves!",
                style = MaterialTheme.typography.titleLarge
            )
        }

        Spacer(Modifier.height(8.dp))

        Button(onClick = {
            cards = createCards()
            firstIndex = null
            moves = 0
            isBusy = false
        }) {
            Text("Restart")
        }
    }
}

