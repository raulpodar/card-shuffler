
package com.raulp.cardshuffler.compose.feature.details

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.raulp.cardshuffler.compose.core.designsystem.component.CardShufflerText
import com.raulp.cardshuffler.compose.core.designsystem.theme.CardShufflerTheme
import com.raulp.cardshuffler.compose.core.model.FlashcardInfo
import com.raulp.cardshuffler.compose.core.navigation.currentComposeNavigator
import kotlin.math.max
import kotlin.math.roundToInt

@Composable
fun SharedTransitionScope.CardShufflerDetails(
  animatedVisibilityScope: AnimatedVisibilityScope,
  detailsViewModel: DetailsViewModel = hiltViewModel(),
) {
    val uiState by detailsViewModel.uiState.collectAsStateWithLifecycle()
    val cardInfo by detailsViewModel.flashcardInfo.collectAsStateWithLifecycle()
    val sessionCorrectAnswers by detailsViewModel.sessionCorrectAnswers.collectAsStateWithLifecycle()
    val sessionTotalAnswers by detailsViewModel.sessionTotalAnswers.collectAsStateWithLifecycle()
    val sessionAnswers by detailsViewModel.sessionAnswers.collectAsStateWithLifecycle()
    var isFlipped by remember { mutableStateOf(false) }

    val onKnow: () -> Unit = {
        detailsViewModel.onKnowClick()
        isFlipped = false
    }

    val onDoNotKnow: () -> Unit = {
        detailsViewModel.onDoNotKnowClick()
        isFlipped = false
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .testTag("CardShufflerDetails"),
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
        ) {
            DetailsHeader(
                backgroundBrush = Brush.verticalGradient(
                    colors = listOf(
                        Color.Transparent,
                        Color.Transparent
                    )
                ),
                flashcardInfo = cardInfo,
                sessionCorrectAnswers = sessionCorrectAnswers,
                sessionTotalAnswers = sessionTotalAnswers,
                sessionAnswers = sessionAnswers
            )
            if (uiState == DetailsUiState.Idle && cardInfo != null) {
                DetailsInfo(
                    flashcardInfo = cardInfo!!,
                    isFlipped = isFlipped,
                    onKnowClick = onKnow,
                    onDoNotKnowClick = onDoNotKnow
                )
            } else {
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
        val buttonColors = when {
            isFlipped -> ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
            else -> ButtonDefaults.buttonColors(containerColor = Color(0xFFF44336))
        }
        Button(
            onClick = { isFlipped = !isFlipped },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            colors = buttonColors
        ) {
            Text(text = if (isFlipped) "show_question" else "show_answer")
        }
        if (isFlipped) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = onKnow,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)),
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = "I knew this")
                }
                Spacer(modifier = Modifier.size(16.dp))
                Button(
                    onClick = onDoNotKnow,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF44336)),
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = "I didn't know this")
                }
            }
        }
    }
}

@Composable
private fun DetailsHeader(
    backgroundBrush: Brush,
    flashcardInfo: FlashcardInfo?,
    sessionCorrectAnswers: Int,
    sessionTotalAnswers: Int,
    sessionAnswers: List<Boolean>
) {
    val composeNavigator = currentComposeNavigator
    val shape = RoundedCornerShape(
        topStart = 0.dp,
        topEnd = 0.dp,
        bottomStart = 64.dp,
        bottomEnd = 64.dp,
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .shadow(elevation = 9.dp, shape = shape)
            .background(brush = backgroundBrush, shape = shape),
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .statusBarsPadding(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                modifier = Modifier
                    .padding(end = 6.dp)
                    .clickable { composeNavigator.navigateUp() },
                painter = painterResource(id = android.R.drawable.ic_media_previous),
                tint = CardShufflerTheme.colors.black,
                contentDescription = null,
            )
        }
        if (flashcardInfo != null) {
            Column(
                modifier = Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                val proficiency =
                    if (flashcardInfo.totalAnswers > 0) (flashcardInfo.correctAnswers.toFloat() / max(
                        flashcardInfo.totalAnswers,
                        10
                    ).toFloat() * 100).toInt() else 0
                FlashcardProficiencyIndicator(proficiency = proficiency)
                Spacer(modifier = Modifier.height(16.dp))
                StreakIndicator(answerHistory = flashcardInfo.answerHistory)
                Spacer(modifier = Modifier.height(16.dp))
                SessionPerformanceIndicator(
                    correctAnswers = sessionCorrectAnswers,
                    totalAnswers = sessionTotalAnswers
                )
                Spacer(modifier = Modifier.height(16.dp))
                SessionStreakIndicator(sessionAnswers = sessionAnswers)
            }
        }
    }
}

@Composable
private fun DetailsInfo(
    flashcardInfo: FlashcardInfo,
    isFlipped: Boolean,
    onKnowClick: () -> Unit,
    onDoNotKnowClick: () -> Unit
) {
    var offsetX by remember { mutableStateOf(0f) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 14.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AnimatedContent(
            targetState = isFlipped,
            transitionSpec = {
                fadeIn(animationSpec = tween(durationMillis = 300)) togetherWith
                        fadeOut(animationSpec = tween(durationMillis = 300))
            }, label = ""
        ) { targetState ->
            Box(
                modifier = Modifier.pointerInput(Unit) {
                    if (targetState) {
                        detectHorizontalDragGestures(
                            onDragEnd = {
                                if (offsetX > 0) {
                                    onKnowClick()
                                } else if (offsetX < 0) {
                                    onDoNotKnowClick()
                                }
                                offsetX = 0f
                            }
                        ) { change, dragAmount ->
                            change.consume()
                            offsetX += dragAmount
                        }
                    }
                }
            ) {
                if (targetState) {
                    CardDetailItem(
                        title = flashcardInfo.answer,
                        modifier = Modifier.offset { IntOffset(offsetX.roundToInt(), 0) }
                    )
                } else {
                    CardDetailItem(
                        title = flashcardInfo.question
                    )
                }
            }
        }
    }
}

@Composable
fun CardDetailItem(title: String, modifier: Modifier = Modifier) {
    CardShufflerText(
        text = title,
        color = CardShufflerTheme.colors.black,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        fontSize = 24.sp,
        modifier = modifier.padding(horizontal = 16.dp)
    )
}

@Composable
fun FlashcardProficiencyIndicator(proficiency: Int) {
    val color = when {
        proficiency >= 80 -> Color(0xFF4CAF50)
        proficiency in 50..79 -> Color(0xFFFFC107)
        else -> Color(0xFFF44336)
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .background(color),
            contentAlignment = Alignment.Center
        ) {
            Text("$proficiency%", color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(8.dp))

        CardShufflerText(
            text = when {
                proficiency >= 80 -> "Mastered"
                proficiency in 50..79 -> "Review Needed"
                else -> "Needs Practice"
            },
            color = CardShufflerTheme.colors.black,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
        )
    }
}

@Composable
fun StreakIndicator(answerHistory: List<Boolean>) {
    Row(horizontalArrangement = Arrangement.Center) {
        for (i in 0 until 10) {
            val color = when {
                i < answerHistory.size && answerHistory[i] -> Color(0xFF4CAF50)
                i < answerHistory.size && !answerHistory[i] -> Color.Red
                else -> Color.Gray
            }
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .padding(2.dp)
                    .background(color, shape = CircleShape)
            )
        }
    }
}

@Composable
fun SessionStreakIndicator(sessionAnswers: List<Boolean>) {
    Row(horizontalArrangement = Arrangement.Center) {
        for (i in 0 until 20) {
            val color = when {
                i < sessionAnswers.size && sessionAnswers[i] -> Color(0xFF4CAF50)
                i < sessionAnswers.size && !sessionAnswers[i] -> Color.Red
                else -> Color.Gray
            }
            Box(
                modifier = Modifier
                    .size(16.dp)
                    .padding(2.dp)
                    .background(color, shape = RoundedCornerShape(2.dp))
            )
        }
    }
}

@Composable
fun SessionPerformanceIndicator(correctAnswers: Int, totalAnswers: Int) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        CardShufflerText(
            text = "Session Performance",
            color = CardShufflerTheme.colors.black,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
        )
        Spacer(modifier = Modifier.height(8.dp))
        CardShufflerText(
            text = "$correctAnswers / $totalAnswers",
            color = CardShufflerTheme.colors.black,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
        )
    }
}

@Preview
@Composable
fun FlashcardProficiencyIndicatorPreviewMastered() {
    CardShufflerTheme {
        FlashcardProficiencyIndicator(proficiency = 90)
    }
}

@Preview
@Composable
fun FlashcardProficiencyIndicatorPreviewReview() {
    CardShufflerTheme {
        FlashcardProficiencyIndicator(proficiency = 65)
    }
}
