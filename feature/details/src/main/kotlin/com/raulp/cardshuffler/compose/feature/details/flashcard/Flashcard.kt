import android.util.Log

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex // Make sure this is imported

private const val TAG = "FlashcardDebug"

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Flashcard(
  question: String,
  answer: String,
  onKnewThis: () -> Unit,
  onNeedToPractice: () -> Unit,
  modifier: Modifier = Modifier
) {
  var rotated by remember { mutableStateOf(false) } // Renamed 'flipped' to 'rotated' to match article
  val rotation by animateFloatAsState(
    targetValue = if (rotated) 180f else 0f,
    animationSpec = tween(
      durationMillis = 400,
      easing = FastOutSlowInEasing
    ),
    label = "cardFlipAnimation" // Added a label
  )

  // Log the rotation value to confirm it's changing smoothly
  Log.d(TAG, "Rotation: $rotation, rotated: $rotated")


  Box(
    modifier = modifier
      .fillMaxWidth()
      .height(200.dp) // Maintain consistent height
      .clickable { rotated = !rotated }, // Toggle 'rotated' on click
    contentAlignment = Alignment.Center
  ) {
    // Front Card
    CardFace(
      modifier = Modifier
        .fillMaxSize()
        .graphicsLayer {
          rotationY = rotation
          cameraDistance = 12f * density
        }
        .zIndex(if (rotation <= 90f) 1f else 0f), // Front card on top for first half
      showFront = true,
      question = question
    )

    // Back Card
    CardFace(
      modifier = Modifier
        .fillMaxSize()
        .graphicsLayer {
          rotationY = rotation - 180f // Correct rotation for back side
          cameraDistance = 12f * density
        }
        .zIndex(if (rotation > 90f) 1f else 0f), // Back card on top for second half
      showFront = false,
      answer = answer,
      onKnewThis = onKnewThis,
      onNeedToPractice = onNeedToPractice
    )
  }
}

// Helper Composable to represent either the front or back of the card
@Composable
fun CardFace(
  modifier: Modifier = Modifier,
  showFront: Boolean,
  question: String? = null, // Nullable for back side
  answer: String? = null, // Nullable for front side
  onKnewThis: (() -> Unit)? = null, // Nullable for front side
  onNeedToPractice: (() -> Unit)? = null // Nullable for front side
) {
  if (showFront) {
    Box(
      modifier = modifier
        .background(MaterialTheme.colorScheme.primary, RoundedCornerShape(16.dp))
        .padding(16.dp),
      contentAlignment = Alignment.Center
    ) {
      Text(
        text = question ?: "No Question", // Fallback for safety
        color = MaterialTheme.colorScheme.onPrimary,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center
      )
    }
  } else {
    Column(
      modifier = modifier
        .background(MaterialTheme.colorScheme.secondary, RoundedCornerShape(16.dp))
        .padding(16.dp),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Text(
        text = answer ?: "No Answer", // Fallback for safety
        color = MaterialTheme.colorScheme.onSecondary,
        fontSize = 20.sp,
        textAlign = TextAlign.Center,
        modifier = Modifier.weight(1f)
      )
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(top = 16.dp)
      ) {
        Button(
          onClick = onKnewThis ?: {}, // Provide empty lambda if null
          modifier = Modifier
            .weight(1f)
            .padding(end = 8.dp)
        ) {
          Text("I knew this")
        }
        Button(
          onClick = onNeedToPractice ?: {}, // Provide empty lambda if null
          modifier = Modifier
            .weight(1f)
            .padding(start = 8.dp)
        ) {
          Text("I need to practice")
        }
      }
    }
  }
}