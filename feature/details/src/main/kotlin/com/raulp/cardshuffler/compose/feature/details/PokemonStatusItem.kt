package com.raulp.cardshuffler.compose.feature.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.raulp.cardshuffler.compose.core.designsystem.component.CardShufflerProgressBar
import com.raulp.cardshuffler.compose.core.designsystem.theme.CardShufflerTheme

@Composable
internal fun PokemonStatusItem(
  modifier: Modifier = Modifier,
  CardShufflerStatus: CardShufflerStatus,
) {
  Row(
    modifier = modifier,
    horizontalArrangement = Arrangement.SpaceEvenly,
  ) {
    Text(
      modifier = Modifier
        .padding(start = 32.dp)
        .widthIn(min = 20.dp),
      text = CardShufflerStatus.type,
      color = CardShufflerTheme.colors.white70,
      fontWeight = FontWeight.Bold,
      fontSize = 12.sp,
    )

    CardShufflerProgressBar(
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp),
      progress = CardShufflerStatus.progress,
      color = CardShufflerStatus.color,
      label = CardShufflerStatus.label,
    )
  }
}
