//
//
//import androidx.compose.animation.core.FastOutSlowInEasing
//import androidx.compose.animation.core.animateFloatAsState
//import androidx.compose.animation.core.tween
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.material3.Card
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.graphicsLayer
//import androidx.compose.ui.unit.dp
//
//private const val TAG = "FlashcardDebug"
//
//
//
//
//enum class CardFace(val angle: Float) {
//  Front(0f) {
//    override val next: CardFace
//      get() = Back
//  },
//  Back(180f) {
//    override val next: CardFace
//      get() = Front
//  };
//
//  abstract val next: CardFace
//}
//
//enum class RotationAxis {
//  AxisX,
//  AxisY,
//}
//
//@Composable
//fun FlipCard(
//  cardFace: CardFace,
//  onClick: (CardFace) -> Unit,
//  modifier: Modifier = Modifier,
//  axis: RotationAxis = RotationAxis.AxisY,
//  back: @Composable () -> Unit = {},
//  front: @Composable () -> Unit = {},
//) {
//  val rotation = animateFloatAsState(
//    targetValue = cardFace.angle,
//    animationSpec = tween(
//      durationMillis = 400,
//      easing = FastOutSlowInEasing,
//    )
//  )
//  Card(
//    onClick = { onClick(cardFace) },
//    modifier = modifier
//      .graphicsLayer {
//        if (axis == RotationAxis.AxisX) {
//          rotationX = rotation.value
//        } else {
//          rotationY = rotation.value
//        }
//        cameraDistance = 12f * density
//      },
//  ) {
//    if (rotation.value <= 90f) {
//      Box(
//        Modifier.fillMaxSize()
//      ) {
//        front()
//      }
//    } else {
//      Box(
//        Modifier
//          .fillMaxSize()
//          .graphicsLayer {
//            if (axis == RotationAxis.AxisX) {
//              rotationX = 180f
//            } else {
//              rotationY = 180f
//            }
//          },
//      ) {
//        back()
//      }
//    }
//  }
//}
//
////@Composable
////fun FlashcardExample() {
////
////  FlipCard(
////    modifier = Modifier
////      .size(200.dp)
////      .padding(16.dp),
////    cardFace = cardFace,
////    onClick = { cardFace = cardFace.next() },
////    front = {
////      Text(
////        text = "What is the capital of France?",
////        style = MaterialTheme.typography.bodyLarge,
////        modifier = Modifier.padding(16.dp)
////      )
////    },
////    back = {
////      Text(
////        text = "Paris",
////        style = MaterialTheme.typography.bodyLarge,
////        modifier = Modifier.padding(16.dp)
////      )
////    }
////  )
////}
//@Composable
//fun FlipCardExample() {
//  var cardFace by remember { mutableStateOf(CardFace.Front) }
//
//  FlipCard(
//    cardFace = cardFace,
//    onClick = { cardFace = cardFace.next },
//    modifier = Modifier
//      .size(200.dp)
//      .padding(16.dp),
//    axis = RotationAxis.AxisY,
//    front = {
//      Box(
//        Modifier
//          .fillMaxSize()
//          .background(Color.Blue),
//        contentAlignment = Alignment.Center
//      ) {
//        Text(text = "Question?", color = Color.White)
//      }
//    },
//    back = {
//      Box(
//        Modifier
//          .fillMaxSize()
//          .background(Color.Green),
//        contentAlignment = Alignment.Center
//      ) {
//        Text(text = "Answer!", color = Color.White)
//      }
//    }
//  )
//}
