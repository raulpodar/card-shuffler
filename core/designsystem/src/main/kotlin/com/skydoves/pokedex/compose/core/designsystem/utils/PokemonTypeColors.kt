package com.raulp.cardshuffler.compose.core.designsystem.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.raulp.cardshuffler.compose.core.designsystem.theme.CardShufflerTheme

@Composable
fun getPokemonTypeColor(type: String): Color {
  return when (type) {
    "fighting" -> CardShufflerTheme.colors.fighting
    "flying" -> CardShufflerTheme.colors.flying
    "poison" -> CardShufflerTheme.colors.poison
    "ground" -> CardShufflerTheme.colors.ground
    "rock" -> CardShufflerTheme.colors.rock
    "bug" -> CardShufflerTheme.colors.bug
    "ghost" -> CardShufflerTheme.colors.ghost
    "steel" -> CardShufflerTheme.colors.steel
    "fire" -> CardShufflerTheme.colors.fire
    "water" -> CardShufflerTheme.colors.water
    "grass" -> CardShufflerTheme.colors.grass
    "electric" -> CardShufflerTheme.colors.electric
    "psychic" -> CardShufflerTheme.colors.psychic
    "ice" -> CardShufflerTheme.colors.ice
    "dragon" -> CardShufflerTheme.colors.dragon
    "fairy" -> CardShufflerTheme.colors.fairy
    "dark" -> CardShufflerTheme.colors.dark
    else -> CardShufflerTheme.colors.gray21
  }
}
