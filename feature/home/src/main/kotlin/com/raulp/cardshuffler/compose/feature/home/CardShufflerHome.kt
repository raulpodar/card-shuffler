package com.raulp.cardshuffler.compose.feature.home

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kmpalette.palette.graphics.Palette
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.glide.GlideImage
import com.skydoves.landscapist.palette.PalettePlugin
import com.skydoves.landscapist.palette.rememberPaletteState
import com.raulp.cardshuffler.compose.core.data.repository.home.FakeHomeRepository
import com.raulp.cardshuffler.compose.core.designsystem.component.CardShufflerAppBar
import com.raulp.cardshuffler.compose.core.designsystem.component.CardShufflerCircularProgress
import com.raulp.cardshuffler.compose.core.designsystem.component.cardShufflerSharedElement
import com.raulp.cardshuffler.compose.core.designsystem.theme.CardShufflerTheme
import com.raulp.cardshuffler.compose.core.model.Pokemon
import com.raulp.cardshuffler.compose.core.navigation.CardShufflerScreen
import com.raulp.cardshuffler.compose.core.navigation.boundsTransform
import com.raulp.cardshuffler.compose.core.navigation.currentComposeNavigator
import com.raulp.cardshuffler.compose.core.preview.CardShufflerPreviewTheme
import com.raulp.cardshuffler.compose.core.preview.PreviewUtils
import com.raulp.cardshuffler.compose.designsystem.R
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

@Composable
fun SharedTransitionScope.CardShufflerHome(
  animatedVisibilityScope: AnimatedVisibilityScope,
  homeViewModel: HomeViewModel = hiltViewModel(),
) {
  val uiState by homeViewModel.uiState.collectAsStateWithLifecycle()
  val pokemonList by homeViewModel.pokemonList.collectAsStateWithLifecycle()

  Column(modifier = Modifier.fillMaxSize()) {
    CardShufflerAppBar()

    HomeContent(
      animatedVisibilityScope = animatedVisibilityScope,
      uiState = uiState,
      pokemonList = pokemonList.toImmutableList(),
      fetchNextPokemonList = homeViewModel::fetchNextPokemonList,
    )
  }
}

@Composable
private fun SharedTransitionScope.HomeContent(
  animatedVisibilityScope: AnimatedVisibilityScope,
  uiState: HomeUiState,
  pokemonList: ImmutableList<Pokemon>,
  fetchNextPokemonList: () -> Unit,
) {
  Box(modifier = Modifier.fillMaxSize()) {
    val threadHold = 8
    LazyVerticalGrid(
      modifier = Modifier.testTag("CardShufflerList"),
      columns = GridCells.Fixed(2),
      contentPadding = PaddingValues(6.dp),
    ) {
      itemsIndexed(items = pokemonList, key = { _, pokemon -> pokemon.name }) { index, pokemon ->
        if ((index + threadHold) >= pokemonList.size && uiState != HomeUiState.Loading) {
          fetchNextPokemonList()
        }

        var palette by rememberPaletteState()
        val backgroundColor by palette.paletteBackgroundColor()

        PokemonCard(
          animatedVisibilityScope = animatedVisibilityScope,
          pokemon = pokemon,
          onPaletteLoaded = { palette = it },
          backgroundColor = backgroundColor,
        )
      }
    }

    if (uiState == HomeUiState.Loading) {
      CardShufflerCircularProgress()
    }
  }
}

@Composable
private fun SharedTransitionScope.PokemonCard(
  animatedVisibilityScope: AnimatedVisibilityScope,
  onPaletteLoaded: (Palette) -> Unit,
  backgroundColor: Color,
  pokemon: Pokemon,
) {
  val composeNavigator = currentComposeNavigator

  Card(
    modifier = Modifier
      .padding(6.dp)
      .fillMaxWidth()
      .testTag("Pokemon")
      .clickable {
        composeNavigator.navigate(CardShufflerScreen.Details(pokemon = pokemon))
      },
    shape = RoundedCornerShape(14.dp),
    colors = CardColors(
      containerColor = backgroundColor,
      contentColor = backgroundColor,
      disabledContainerColor = backgroundColor,
      disabledContentColor = backgroundColor,
    ),
    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
  ) {
    GlideImage(
      modifier = Modifier
        .align(Alignment.CenterHorizontally)
        .padding(top = 20.dp)
        .size(120.dp)
        .cardShufflerSharedElement(
          isLocalInspectionMode = LocalInspectionMode.current,
          state = rememberSharedContentState(key = "image-${pokemon.name}"),
          animatedVisibilityScope = animatedVisibilityScope,
          boundsTransform = boundsTransform,
        ),
      imageModel = { pokemon.imageUrl },
      imageOptions = ImageOptions(contentScale = ContentScale.Inside),
      component = rememberImageComponent {

        if (!LocalInspectionMode.current) {
          +PalettePlugin(
            imageModel = pokemon.imageUrl,
            useCache = true,
            paletteLoadedListener = { onPaletteLoaded.invoke(it) },
          )
        }
      },
      previewPlaceholder = painterResource(
        id = R.drawable.pokemon_preview,
      ),
    )

    Text(
      modifier = Modifier
        .align(Alignment.CenterHorizontally)
        .fillMaxWidth()
        .cardShufflerSharedElement(
          isLocalInspectionMode = LocalInspectionMode.current,
          state = rememberSharedContentState(key = "name-${pokemon.name}"),
          animatedVisibilityScope = animatedVisibilityScope,
          boundsTransform = boundsTransform,
        )
        .padding(12.dp),
      text = pokemon.name,
      color = CardShufflerTheme.colors.black,
      textAlign = TextAlign.Center,
      fontSize = 16.sp,
      fontWeight = FontWeight.Bold,
    )
  }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun CardShufflerHomePreview() {
  CardShufflerTheme {
    SharedTransitionScope {
      AnimatedVisibility(visible = true, label = "") {
        CardShufflerHome(
          animatedVisibilityScope = this,
          homeViewModel = HomeViewModel(homeRepository = FakeHomeRepository()),
        )
      }
    }
  }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun HomeContentPreview() {
  CardShufflerPreviewTheme { scope ->
    HomeContent(
      animatedVisibilityScope = scope,
      uiState = HomeUiState.Idle,
      pokemonList = PreviewUtils.mockPokemonList().toImmutableList(),
      fetchNextPokemonList = { HomeViewModel(homeRepository = FakeHomeRepository()) },
    )
  }
}
