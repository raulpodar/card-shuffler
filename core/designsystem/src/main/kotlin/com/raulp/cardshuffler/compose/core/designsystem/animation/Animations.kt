package com.raulp.cardshuffler.compose.core.designsystem.animation

import androidx.compose.animation.ContentTransform
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember


fun fancySlideTransition(
  isForward: Boolean,
  screenWidthPx: Int,
  duration: Int = 600
): ContentTransform = if (isForward) {
  slideInHorizontally(
    animationSpec = tween(duration, easing = FancyTransitionEasing),
    initialOffsetX = { screenWidthPx }) + fadeIn(
    tween(300, 100)
  ) togetherWith slideOutHorizontally(
    animationSpec = tween(duration, easing = FancyTransitionEasing),
    targetOffsetX = { -screenWidthPx }) + fadeOut(
    tween(300, 100)
  )
} else {
  slideInHorizontally(
    animationSpec = tween(600, easing = FancyTransitionEasing),
    initialOffsetX = { -screenWidthPx }) + fadeIn(
    tween(300, 100)
  ) togetherWith slideOutHorizontally(
    animationSpec = tween(600, easing = FancyTransitionEasing),
    targetOffsetX = { screenWidthPx }) + fadeOut(
    tween(300, 100)
  )
}

val PageOpenTransition = slideInHorizontally(
  openCloseTransitionSpec()
) { -it / 3 } + fadeIn(
  openCloseTransitionSpec(500)
)

val PageCloseTransition = slideOutHorizontally(
  openCloseTransitionSpec()
) { -it / 3 } + fadeOut(
  openCloseTransitionSpec(500)
)

private fun <T> openCloseTransitionSpec(
  duration: Int = 500,
  delay: Int = 0
) = tween<T>(
  durationMillis = duration,
  delayMillis = delay,
  easing = TransitionEasing
)


@Composable
fun animateFloatingRangeAsState(
  range: ClosedFloatingPointRange<Float>,
  animationSpec: AnimationSpec<Float> = spring()
): State<ClosedFloatingPointRange<Float>> {
  val start = animateFloatAsState(
    targetValue = range.start,
    animationSpec = animationSpec
  )

  val end = animateFloatAsState(
    targetValue = range.endInclusive,
    animationSpec = animationSpec
  )

  return remember(start, end) {
    derivedStateOf {
      start.value..end.value
    }
  }
}