package com.raulp.cardshuffler.compose.feature.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.raulp.cardshuffler.compose.core.designsystem.component.CardShufflerText
import com.raulp.cardshuffler.compose.core.designsystem.theme.CardShufflerTheme

@Composable
internal fun CardDetailItem(
  title: String?,
  modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CardShufflerText(
            modifier = Modifier.padding(10.dp),
            text = title.orEmpty(),
            previewText = "24.0 KG",
            color = CardShufflerTheme.colors.black,
            fontWeight = FontWeight.Bold,
            fontSize = 21.sp,
            textAlign = TextAlign.Center
        )
    }
}