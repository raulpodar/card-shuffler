package com.raulp.cardshuffler.compose.feature.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import com.kmpalette.palette.graphics.Palette
import com.raulp.cardshuffler.compose.core.designsystem.theme.CardShufflerTheme

@Composable
internal fun Palette?.paletteBackgroundColor(): State<Color> {
  val defaultBackground = CardShufflerTheme.colors.background
  return remember(this) {
    derivedStateOf {
      val rgb = this?.dominantSwatch?.rgb
      if (rgb != null) {
        Color(rgb)
      } else {
        defaultBackground
      }
    }
  }
}
