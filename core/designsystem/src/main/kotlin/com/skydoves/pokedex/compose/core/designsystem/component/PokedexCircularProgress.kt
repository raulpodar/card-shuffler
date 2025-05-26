package com.raulp.cardshuffler.compose.core.designsystem.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.raulp.cardshuffler.compose.core.designsystem.theme.CardShufflerTheme

@Composable
fun BoxScope.CardShufflerCircularProgress() {
  CircularProgressIndicator(
    modifier = Modifier.align(Alignment.Center),
    color = CardShufflerTheme.colors.primary,
  )
}

@Preview
@Composable
private fun CardShufflerCircularProgressPreview() {
  CardShufflerTheme {
    Box(modifier = Modifier.fillMaxSize()) {
      CardShufflerCircularProgress()
    }
  }
}
