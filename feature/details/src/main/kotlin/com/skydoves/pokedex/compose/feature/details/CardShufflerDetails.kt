package com.raulp.cardshuffler.compose.feature.details

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kmpalette.palette.graphics.Palette
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.glide.GlideImage
import com.skydoves.landscapist.palette.PalettePlugin
import com.skydoves.landscapist.palette.rememberPaletteState
import com.raulp.cardshuffler.compose.core.data.repository.details.FakeDetailsRepository
import com.raulp.cardshuffler.compose.core.designsystem.component.CardShufflerCircularProgress
import com.raulp.cardshuffler.compose.core.designsystem.component.CardShufflerText
import com.raulp.cardshuffler.compose.core.designsystem.component.cardShufflerSharedElement
import com.raulp.cardshuffler.compose.core.designsystem.theme.CardShufflerTheme
import com.raulp.cardshuffler.compose.core.designsystem.utils.getPokemonTypeColor
import com.raulp.cardshuffler.compose.core.model.Pokemon
import com.raulp.cardshuffler.compose.core.model.PokemonInfo
import com.raulp.cardshuffler.compose.core.navigation.boundsTransform
import com.raulp.cardshuffler.compose.core.navigation.currentComposeNavigator
import com.raulp.cardshuffler.compose.core.preview.CardShufflerPreviewTheme
import com.raulp.cardshuffler.compose.core.preview.PreviewUtils
import com.raulp.cardshuffler.compose.designsystem.R

@Composable
fun SharedTransitionScope.CardShufflerDetails(
  animatedVisibilityScope: AnimatedVisibilityScope,
  detailsViewModel: DetailsViewModel = hiltViewModel(),
) {
  val uiState by detailsViewModel.uiState.collectAsStateWithLifecycle()
  val pokemon by detailsViewModel.pokemon.collectAsStateWithLifecycle()
  val pokemonInfo by detailsViewModel.pokemonInfo.collectAsStateWithLifecycle()

  Column(
    modifier = Modifier
      .fillMaxSize()
      .verticalScroll(rememberScrollState())
      .testTag("CardShufflerDetails"),
  ) {

    var palette by rememberPaletteState()
    val backgroundBrush by palette.paletteBackgroundBrush()

    DetailsHeader(
      animatedVisibilityScope = animatedVisibilityScope,
      pokemon = pokemon,
      pokemonInfo = pokemonInfo,
      onPaletteLoaded = { palette = it },
      backgroundBrush = backgroundBrush
    )

    if (uiState == DetailsUiState.Idle && pokemonInfo != null) {
      DetailsInfo(pokemonInfo = pokemonInfo!!)

      DetailsStatus(pokemonInfo = pokemonInfo!!)
    } else {
      Box(modifier = Modifier.fillMaxSize()) {
        CardShufflerCircularProgress()
      }
    }
  }
}

@Composable
private fun SharedTransitionScope.DetailsHeader(
  animatedVisibilityScope: AnimatedVisibilityScope,
  pokemon: Pokemon?,
  pokemonInfo: PokemonInfo?,
  onPaletteLoaded: (Palette) -> Unit,
  backgroundBrush: Brush,
) {
  val composeNavigator = currentComposeNavigator
  val shape = RoundedCornerShape(
    topStart = 0.dp,
    topEnd = 0.dp,
    bottomStart = 64.dp,
    bottomEnd = 64.dp,
  )

  Box(
    modifier = Modifier
      .fillMaxWidth()
      .height(290.dp)
      .shadow(elevation = 9.dp, shape = shape)
      .background(brush = backgroundBrush, shape = shape),
  ) {
    Row(
      modifier = Modifier
        .padding(12.dp)
        .statusBarsPadding(),
      verticalAlignment = Alignment.CenterVertically,
    ) {
      Icon(
        modifier = Modifier
          .padding(end = 6.dp)
          .clickable { composeNavigator.navigateUp() },
        painter = painterResource(id = R.drawable.ic_arrow),
        tint = CardShufflerTheme.colors.absoluteWhite,
        contentDescription = null,
      )

      Text(
        modifier = Modifier.padding(horizontal = 10.dp),
        text = pokemon?.name.orEmpty(),
        color = CardShufflerTheme.colors.absoluteWhite,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
      )
    }

    CardShufflerText(
      modifier = Modifier
        .align(Alignment.TopEnd)
        .padding(12.dp)
        .statusBarsPadding(),
      text = pokemonInfo?.getIdString().orEmpty(),
      previewText = "#001",
      color = CardShufflerTheme.colors.absoluteWhite,
      fontWeight = FontWeight.Bold,
      fontSize = 18.sp,
    )

    GlideImage(
      modifier = Modifier
        .align(Alignment.BottomCenter)
        .padding(bottom = 20.dp)
        .size(190.dp)
        .cardShufflerSharedElement(
          isLocalInspectionMode = LocalInspectionMode.current,
          state = rememberSharedContentState(key = "image-${pokemon?.name}"),
          animatedVisibilityScope = animatedVisibilityScope,
          boundsTransform = boundsTransform,
        ),
      imageModel = { pokemon?.imageUrl },
      imageOptions = ImageOptions(contentScale = ContentScale.Inside),
      component = rememberImageComponent {
        if (!LocalInspectionMode.current) {
          +PalettePlugin(
            imageModel = pokemon?.imageUrl,
            useCache = true,
            paletteLoadedListener = { onPaletteLoaded.invoke(it) },
          )
        }
      },
      previewPlaceholder = painterResource(
        id = R.drawable.pokemon_preview,
      ),
    )
  }

  CardShufflerText(
    modifier = Modifier
      .padding(top = 24.dp)
      .fillMaxWidth()
      .cardShufflerSharedElement(
        isLocalInspectionMode = LocalInspectionMode.current,
        state = rememberSharedContentState(key = "name-${pokemon?.name}"),
        animatedVisibilityScope = animatedVisibilityScope,
        boundsTransform = boundsTransform,
      ),
    text = pokemon?.name.orEmpty(),
    previewText = "skydoves",
    color = CardShufflerTheme.colors.black,
    fontWeight = FontWeight.Bold,
    textAlign = TextAlign.Center,
    fontSize = 36.sp,
  )
}

@Composable
private fun DetailsInfo(pokemonInfo: PokemonInfo) {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .padding(top = 14.dp),
    horizontalArrangement = Arrangement.spacedBy(22.dp, Alignment.CenterHorizontally),
  ) {
    pokemonInfo.types.forEach { typeInfo ->
      Text(
        modifier = Modifier
          .background(
            color = getPokemonTypeColor(type = typeInfo.type.name),
            shape = RoundedCornerShape(64.dp),
          )
          .padding(horizontal = 40.dp, vertical = 4.dp),
        text = typeInfo.type.name,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        color = CardShufflerTheme.colors.absoluteWhite,
        maxLines = 1,
        fontSize = 16.sp,
      )
    }
  }

  Row(
    modifier = Modifier
      .fillMaxWidth()
      .padding(top = 24.dp),
    horizontalArrangement = Arrangement.SpaceEvenly,
  ) {
    PokemonInfoItem(
      title = pokemonInfo.getWeightString(),
      content = stringResource(id = R.string.weight),
    )

    PokemonInfoItem(
      title = pokemonInfo.getHeightString(),
      content = stringResource(id = R.string.height),
    )
  }
}

@Composable
private fun DetailsStatus(
  pokemonInfo: PokemonInfo,
) {
  Text(
    modifier = Modifier
      .fillMaxWidth()
      .padding(top = 22.dp, bottom = 16.dp),
    text = stringResource(id = R.string.base_stats),
    textAlign = TextAlign.Center,
    color = CardShufflerTheme.colors.black,
    fontWeight = FontWeight.Bold,
    fontSize = 21.sp,
  )

  Column {
    pokemonInfo.toCardShufflerStatusList().forEach { pokemonStatus ->
      PokemonStatusItem(
        modifier = Modifier.padding(bottom = 12.dp),
        CardShufflerStatus = pokemonStatus,
      )
    }
  }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun CardShufflerDetailsPreview() {
  CardShufflerPreviewTheme {
    CardShufflerDetails(
      animatedVisibilityScope = it,
      detailsViewModel = DetailsViewModel(
        detailsRepository = FakeDetailsRepository(),
        savedStateHandle = SavedStateHandle(),
      ),
    )
  }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun CardShufflerDetailsInfoPreview() {
  CardShufflerPreviewTheme {
    DetailsInfo(pokemonInfo = PreviewUtils.mockPokemonInfo())
  }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun CardShufflerDetailsStatusPreview() {
  CardShufflerPreviewTheme {
    DetailsStatus(
      pokemonInfo = PreviewUtils.mockPokemonInfo(),
    )
  }
}
