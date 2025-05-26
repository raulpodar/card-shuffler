package com.raulp.cardshuffler.compose.feature.details

import androidx.annotation.FloatRange
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.raulp.cardshuffler.compose.core.designsystem.theme.CardShufflerTheme
import com.raulp.cardshuffler.compose.core.model.PokemonInfo
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import javax.annotation.concurrent.Immutable

@Immutable
internal data class CardShufflerStatus(
  val type: String,
  @FloatRange(0.0, 1.0) val progress: Float,
  val color: Color,
  val label: String,
)

@Composable
internal fun PokemonInfo.toCardShufflerStatusList(): ImmutableList<CardShufflerStatus> {
  return persistentListOf(
    CardShufflerStatus(
      type = stringResource(id = com.raulp.cardshuffler.compose.designsystem.R.string.hp),
      progress = hp / PokemonInfo.MAX_HP.toFloat(),
      color = CardShufflerTheme.colors.primary,
      label = getHpString(),
    ),
    CardShufflerStatus(
      type = stringResource(id = com.raulp.cardshuffler.compose.designsystem.R.string.atk),
      progress = attack / PokemonInfo.MAX_ATTACK.toFloat(),
      color = CardShufflerTheme.colors.orange,
      label = getAttackString(),
    ),
    CardShufflerStatus(
      type = stringResource(id = com.raulp.cardshuffler.compose.designsystem.R.string.def),
      progress = defense / PokemonInfo.MAX_DEFENSE.toFloat(),
      color = CardShufflerTheme.colors.blue,
      label = getDefenseString(),
    ),
    CardShufflerStatus(
      type = stringResource(id = com.raulp.cardshuffler.compose.designsystem.R.string.spd),
      progress = speed / PokemonInfo.MAX_SPEED.toFloat(),
      color = CardShufflerTheme.colors.flying,
      label = getSpeedString(),
    ),
    CardShufflerStatus(
      type = stringResource(id = com.raulp.cardshuffler.compose.designsystem.R.string.exp),
      progress = exp / PokemonInfo.MAX_EXP.toFloat(),
      color = CardShufflerTheme.colors.green,
      label = getExpString(),
    ),
  )
}
