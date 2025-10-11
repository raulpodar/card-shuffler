
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.raulp.cardshuffler.compose.core.model.FlashcardInfo

@Composable
fun CardListScreen(
    viewModel: CardListViewModel = hiltViewModel()
) {
    val flashcards by viewModel.flashcards.collectAsState()

    LazyColumn {
        items(flashcards) { flashcard ->
            FlashcardItem(flashcard = flashcard, viewModel = viewModel)
        }
    }
}

@Composable
fun FlashcardItem(flashcard: FlashcardInfo, viewModel: CardListViewModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = viewModel.getBackgroundColor(flashcard.subjectId)
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = flashcard.question)
            CircularProgressIndicator(
                progress = if (flashcard.totalAnswers > 0) flashcard.totalCorrectAnswers.toFloat() / flashcard.totalAnswers.toFloat() else 0f
            )
        }
    }
}
