package com.raulp.cardshuffler.compose.feature.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.raulp.cardshuffler.compose.core.designsystem.component.CardShufflerText
import com.raulp.cardshuffler.compose.core.designsystem.theme.CardShufflerTheme

@Composable
internal fun PokemonInfoItem(
  title: String?,
  content: String?,
) {
  Column(horizontalAlignment = Alignment.CenterHorizontally) {
    CardShufflerText(
      modifier = Modifier.padding(10.dp),
      text = title.orEmpty(),
      previewText = "24.0 KG",
      color = CardShufflerTheme.colors.black,
      fontWeight = FontWeight.Bold,
      fontSize = 21.sp,
    )

    CardShufflerText(
      text = content.orEmpty(),
      previewText = "Weight",
      color = CardShufflerTheme.colors.white56,
      fontWeight = FontWeight.Bold,
      fontSize = 12.sp,
    )
  }
}
