

package com.raulp.cardshuffler.compose.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf

public val LocalComposeNavigator:
  ProvidableCompositionLocal<AppComposeNavigator<CardShufflerScreen>> =
  compositionLocalOf {
    error(
      "No AppComposeNavigator provided! " +
        "Make sure to wrap all usages of CardShuffler components in CardShufflerTheme.",
    )
  }

/**
 * Retrieves the current [AppComposeNavigator] at the call site's position in the hierarchy.
 */
public val currentComposeNavigator: AppComposeNavigator<CardShufflerScreen>
  @Composable
  @ReadOnlyComposable
  get() = LocalComposeNavigator.current
