package com.raulp.cardshuffler.compose.core.preview

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.raulp.cardshuffler.compose.core.designsystem.theme.CardShufflerTheme
import com.raulp.cardshuffler.compose.core.navigation.LocalComposeNavigator
import com.raulp.cardshuffler.compose.core.navigation.CardShufflerComposeNavigator

@Composable
fun CardShufflerPreviewTheme(
  content: @Composable SharedTransitionScope.(AnimatedVisibilityScope) -> Unit,
) {
  CompositionLocalProvider(
    LocalComposeNavigator provides CardShufflerComposeNavigator(),
  ) {
    CardShufflerTheme {
      SharedTransitionScope {
        AnimatedVisibility(visible = true, label = "") {
          content(this)
        }
      }
    }
  }
}
