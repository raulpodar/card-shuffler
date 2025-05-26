

package com.raulp.cardshuffler.compose.core.designsystem.component

import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.raulp.cardshuffler.compose.core.designsystem.theme.CardShufflerTheme
import com.raulp.cardshuffler.compose.designsystem.R

@Composable
fun CardShufflerAppBar() {
  TopAppBar(
    title = {
      Text(
        text = stringResource(id = R.string.app_name),
        color = CardShufflerTheme.colors.absoluteWhite,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
      )
    },
    colors = TopAppBarDefaults.topAppBarColors().copy(
      containerColor = CardShufflerTheme.colors.primary,
    ),
  )
}

@Preview
@Composable
private fun CardShufflerAppBarPreview() {
  CardShufflerTheme {
    CardShufflerAppBar()
  }
}
