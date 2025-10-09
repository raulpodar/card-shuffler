package com.raulp.cardshuffler.compose.feature.details.flashcard

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.raulp.cardshuffler.compose.core.designsystem.theme.CardShufflerTheme

@Composable
fun FlashcardStatus(
  question: String,
  answer: String,
  onAnswerSelected: (Boolean) -> Unit
) {
  var isFlipped by remember { mutableStateOf(false) }
  val rotationYval by animateFloatAsState(
    targetValue = if (isFlipped) 180f else 0f,
    animationSpec = tween(durationMillis = 2600),
    label = "card-flip"
  )
  val cameraDistance = 30f * LocalDensity.current.density

  Box(
    modifier = Modifier
      .fillMaxWidth()
      .padding(16.dp)
      .aspectRatio(1.6f)
      .graphicsLayer {
        rotationY = rotationYval
        this.cameraDistance = cameraDistance
      }
      .background(Color.White, RoundedCornerShape(12.dp))
      .clickable { isFlipped = !isFlipped },
    contentAlignment = Alignment.Center
  ) {
    if (rotationYval <= 90f) {
      // FRONT SIDE — QUESTION
      Box(
        modifier = Modifier
          .fillMaxSize()
          .graphicsLayer {
            rotationY = 0f
          }
          .background(Color.White, RoundedCornerShape(12.dp)),
        contentAlignment = Alignment.Center
      ) {
        Text(
          text = question,
          fontSize = 20.sp,
          fontWeight = FontWeight.Bold,
          color = CardShufflerTheme.colors.black,
          modifier = Modifier.padding(24.dp)
        )
      }
    } else {
      // BACK SIDE — ANSWER
      Box(
        modifier = Modifier
          .fillMaxSize()
          .graphicsLayer {
            rotationY = 180f
          }
          .background(Color.White, RoundedCornerShape(12.dp)),
        contentAlignment = Alignment.Center
      ) {
        Column(
          horizontalAlignment = Alignment.CenterHorizontally,
          verticalArrangement = Arrangement.Center,
          modifier = Modifier.padding(24.dp)
        ) {
          Text(
            text = answer,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = CardShufflerTheme.colors.black,
            modifier = Modifier.padding(bottom = 24.dp)
          )
          Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Button(onClick = { onAnswerSelected(true) }) {
              Text("I knew this")
            }
            Button(onClick = { onAnswerSelected(false) }) {
              Text("I need to practice")
            }
          }
        }
      }
    }
  }
}
