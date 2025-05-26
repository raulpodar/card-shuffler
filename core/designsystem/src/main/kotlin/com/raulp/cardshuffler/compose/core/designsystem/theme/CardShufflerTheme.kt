

@file:OptIn(ExperimentalComposeUiApi::class)

package com.raulp.cardshuffler.compose.core.designsystem.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId

/**
 * Local providers for various properties we connect to our components, for styling.
 */
private val LocalColors = compositionLocalOf<CardShufflerColors> {
  error(
    "No colors provided! Make sure to wrap all usages of CardShuffler components in CardShufflerTheme.",
  )
}

@Composable
public fun CardShufflerTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  colors: CardShufflerColors = if (darkTheme) {
    CardShufflerColors.defaultDarkColors()
  } else {
    CardShufflerColors.defaultLightColors()
  },
  background: CardShufflerBackground = CardShufflerBackground.defaultBackground(darkTheme),
  content: @Composable () -> Unit,
) {
  CompositionLocalProvider(
    LocalColors provides colors,
    LocalBackgroundTheme provides background,
  ) {
    Box(
      modifier = Modifier
        .background(background.color)
        .semantics { testTagsAsResourceId = true },
    ) {
      content()
    }
  }
}

/**
 * Contains ease-of-use accessors for different properties used to style and customize the app
 * look and feel.
 */
public object CardShufflerTheme {
  /**
   * Retrieves the current [CardShufflerColors] at the call site's position in the hierarchy.
   */
  public val colors: CardShufflerColors
    @Composable
    @ReadOnlyComposable
    get() = LocalColors.current

  /**
   * Retrieves the current [CardShufflerBackground] at the call site's position in the hierarchy.
   */
  public val background: CardShufflerBackground
    @Composable
    @ReadOnlyComposable
    get() = LocalBackgroundTheme.current
}
