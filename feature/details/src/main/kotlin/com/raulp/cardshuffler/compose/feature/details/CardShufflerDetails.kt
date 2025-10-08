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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
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
import com.raulp.cardshuffler.compose.core.data.repository.details.FakeFlashcardsRepository
import com.raulp.cardshuffler.compose.core.designsystem.component.CardShufflerCircularProgress
import com.raulp.cardshuffler.compose.core.designsystem.component.CardShufflerText
import com.raulp.cardshuffler.compose.core.designsystem.theme.CardShufflerTheme
import com.raulp.cardshuffler.compose.core.designsystem.utils.getPokemonTypeColor
import com.raulp.cardshuffler.compose.core.model.Topic
import com.raulp.cardshuffler.compose.core.model.FlashcardInfo
import com.raulp.cardshuffler.compose.core.navigation.currentComposeNavigator
import com.raulp.cardshuffler.compose.core.preview.CardShufflerPreviewTheme
import com.raulp.cardshuffler.compose.designsystem.R
import com.skydoves.landscapist.palette.rememberPaletteState

@Composable
fun SharedTransitionScope.CardShufflerDetails(
  animatedVisibilityScope: AnimatedVisibilityScope,
  detailsViewModel: DetailsViewModel = hiltViewModel(),
) {
  val uiState by detailsViewModel.uiState.collectAsStateWithLifecycle()
  val pokemon by detailsViewModel.topic.collectAsStateWithLifecycle()
  val pokemonInfo by detailsViewModel.flashcardInfo.collectAsStateWithLifecycle()

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
      topic = pokemon,
      flashcardInfo = pokemonInfo,
      onPaletteLoaded = { palette = it },
      backgroundBrush = backgroundBrush
    )

    if (uiState == DetailsUiState.Idle && pokemonInfo != null) {
      DetailsInfo(flashcardInfo = pokemonInfo!!)

      DetailsStatus(flashcardInfo = pokemonInfo!!)
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
  topic: Topic?,
  flashcardInfo: FlashcardInfo?,
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
      .fillMaxHeight()
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
        text = topic?.topicTitle.orEmpty(),
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
      text = flashcardInfo?.getIdString().orEmpty(),
      previewText = "#001",
      color = CardShufflerTheme.colors.absoluteWhite,
      fontWeight = FontWeight.Bold,
      fontSize = 18.sp,
    )


    // Center the FlashcardProficiencyIndicator
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .padding(top = 20.dp), // Adjust padding as needed for vertical positioning
      contentAlignment = Alignment.Center
    ) {
      FlashcardProficiencyIndicator(proficiency = 35)
    }
  }
}

@Composable
private fun DetailsInfo(flashcardInfo: FlashcardInfo) {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .padding(top = 14.dp),
    horizontalArrangement = Arrangement.spacedBy(22.dp, Alignment.CenterHorizontally),
  ) {
//    flashcardInfo.forEach { typeInfo ->
//      Text(
//        modifier = Modifier
//          .background(
//            color = getPokemonTypeColor(type = typeInfo.type.name),
//            shape = RoundedCornerShape(64.dp),
//          )
//          .padding(horizontal = 40.dp, vertical = 4.dp),
//        text = typeInfo.type.name,
//        fontWeight = FontWeight.Bold,
//        textAlign = TextAlign.Center,
//        color = CardShufflerTheme.colors.absoluteWhite,
//        maxLines = 1,
//        fontSize = 16.sp,
//      )
//    }
  }

  Row(
    modifier = Modifier
      .fillMaxWidth()
      .padding(top = 24.dp),
    horizontalArrangement = Arrangement.SpaceEvenly,
  ) {
    PokemonInfoItem(
      title = flashcardInfo.answer,
      content = stringResource(id = R.string.weight),
    )

    PokemonInfoItem(
      title = flashcardInfo.question,
      content = stringResource(id = R.string.height),
    )
  }
}

@Composable
fun FlashcardProficiencyIndicator(proficiency: Int) {
  val color = when {
    proficiency >= 80 -> Color(0xFF4CAF50)  // Green
    proficiency in 50..79 -> Color(0xFFFFC107) // Yellow
    else -> Color(0xFFF44336) // Red
  }

  Column(horizontalAlignment = Alignment.CenterHorizontally) {
    Box(
      modifier = Modifier
        .size(100.dp)
        .clip(CircleShape)
        .background(color),
      contentAlignment = Alignment.Center
    ) {
      Text("$proficiency%", color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold)
    }

    Spacer(modifier = Modifier.height(8.dp))

    CardShufflerText(
      text = when {
        proficiency >= 80 -> "Mastered"
        proficiency in 50..79 -> "Review Needed"
        else -> "Needs Practice"
      },
      color = CardShufflerTheme.colors.black,
      fontWeight = FontWeight.Bold,
      textAlign = TextAlign.Center,
      fontSize = 24.sp,
    )
  }
}

@Preview
@Composable
fun FlashcardProficiencyIndicatorPreviewMastered() {
  CardShufflerTheme {
    FlashcardProficiencyIndicator(proficiency = 90)
  }
}

@Preview
@Composable
fun FlashcardProficiencyIndicatorPreviewReview() {
  CardShufflerTheme {
    FlashcardProficiencyIndicator(proficiency = 65)
  }
}




@Composable
private fun DetailsStatus(
  flashcardInfo: FlashcardInfo,
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

//  Column {
//    flashcardInfo.toCardShufflerStatusList().forEach { pokemonStatus ->
//      PokemonStatusItem(
//        modifier = Modifier.padding(bottom = 12.dp),
//        CardShufflerStatus = pokemonStatus,
//      )
//    }
//  }
//  Flashcard(
//    question = "What is the capital of France?",
//    answer = "Paris",
//    onKnewThis = {
//      // Handle "I knew this" action, e.g., move to next card, update score
//      println("User knew the answer!")
//    },
//    onNeedToPractice = {
//      // Handle "I need to practice" action, e.g., mark for review
//      println("User needs to practice this one.")
//    }
//  )
//  FlashcardStatus(
//    question = "What is the capital of France?",
//    answer = "Paris",
//    onAnswerSelected = { }
//  )

}


//
//@Preview
//@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
//@Composable
//private fun CardShufflerDetailsPreview() {
//  CardShufflerPreviewTheme {
//    CardShufflerDetails(
//      animatedVisibilityScope = it,
//      detailsViewModel = DetailsViewModel(
//        flashcardsRepository = FakeFlashcardsRepository(),
//        savedStateHandle = SavedStateHandle(),
//      ),
//    )
//  }
//}
//
//@Preview
//@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
//@Composable
//private fun CardShufflerDetailsInfoPreview() {
//  CardShufflerPreviewTheme {
//    DetailsInfo(flashcardInfo = PreviewUtils.mockPokemonInfo())
//  }
//}
//
//@Preview
//@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
//@Composable
//private fun CardShufflerDetailsStatusPreview() {
//  CardShufflerPreviewTheme {
//    DetailsStatus(
//      flashcardInfo = PreviewUtils.mockPokemonInfo(),
//    )
//  }
//}
