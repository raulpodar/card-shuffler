package com.raulp.cardshuffler.compose.core.designsystem.theme

import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MotionScheme
import com.raulp.cardshuffler.compose.core.designsystem.animation.FancyTransitionEasing


@OptIn(ExperimentalMaterial3ExpressiveApi::class)
internal val CardShufflerMotionScheme: MotionScheme = object : MotionScheme {
  val SpringDefaultSpatialDamping = 0.8f
  val SpringDefaultSpatialStiffness = 380.0f
  val SpringDefaultEffectsDamping = 1.0f
  val SpringDefaultEffectsStiffness = 1600.0f
  val SpringFastSpatialDamping = 0.6f
  val SpringFastSpatialStiffness = 800.0f
  val SpringFastEffectsDamping = 1.0f
  val SpringFastEffectsStiffness = 3800.0f
  val SpringSlowSpatialDamping = 0.8f
  val SpringSlowSpatialStiffness = 200.0f
  val SpringSlowEffectsDamping = 1.0f
  val SpringSlowEffectsStiffness = 800.0f

  private val defaultSpatialSpec =
    tween<Any>(
      durationMillis = 400,
      easing = FancyTransitionEasing
    )

  private val fastSpatialSpec =
    spring<Any>(
      dampingRatio = SpringFastSpatialDamping,
      stiffness = SpringFastSpatialStiffness
    )

  private val slowSpatialSpec =
    spring<Any>(
      dampingRatio = SpringSlowSpatialDamping,
      stiffness = SpringSlowSpatialStiffness
    )

  private val defaultEffectsSpec =
    spring<Any>(
      dampingRatio = SpringDefaultEffectsDamping,
      stiffness = SpringDefaultEffectsStiffness
    )

  private val fastEffectsSpec =
    tween<Any>(
      durationMillis = 300,
      easing = FancyTransitionEasing
    )

  private val slowEffectsSpec =
    tween<Any>(
      durationMillis = 500,
      easing = FancyTransitionEasing
    )

  override fun <T> defaultSpatialSpec(): FiniteAnimationSpec<T> = defaultSpatialSpec.cast()

  override fun <T> fastSpatialSpec(): FiniteAnimationSpec<T> = fastSpatialSpec.cast()

  override fun <T> slowSpatialSpec(): FiniteAnimationSpec<T> = slowSpatialSpec.cast()

  override fun <T> defaultEffectsSpec(): FiniteAnimationSpec<T> = defaultEffectsSpec.cast()

  override fun <T> fastEffectsSpec(): FiniteAnimationSpec<T> = fastEffectsSpec.cast()

  override fun <T> slowEffectsSpec(): FiniteAnimationSpec<T> = slowEffectsSpec.cast()
}


inline fun <reified T, reified R> T.cast(): R = this as R

inline fun <reified T, reified R> T.safeCast(): R? = this as? R

inline fun <reified R> Any?.ifCasts(action: (R) -> Unit) = (this as? R)?.let(action)