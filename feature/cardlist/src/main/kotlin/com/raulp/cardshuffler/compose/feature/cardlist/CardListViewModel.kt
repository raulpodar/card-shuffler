
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raulp.cardshuffler.compose.core.data.repository.details.FlashcardsRepository
import com.raulp.cardshuffler.compose.core.model.FlashcardInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class CardListViewModel @Inject constructor(
    private val flashcardsRepository: FlashcardsRepository
) : ViewModel() {

    val flashcards: StateFlow<List<FlashcardInfo>> =
        flashcardsRepository.getAllFlashcards()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = emptyList()
            )

    fun getBackgroundColor(subjectId: String): Color {
        return Color(
            red = Random.nextInt(256),
            green = Random.nextInt(256),
            blue = Random.nextInt(256)
        )
    }
}
