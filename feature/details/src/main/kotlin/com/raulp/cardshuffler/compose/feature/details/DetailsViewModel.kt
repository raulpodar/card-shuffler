
package com.raulp.cardshuffler.compose.feature.details

import androidx.compose.runtime.Stable
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.raulp.cardshuffler.compose.core.data.repository.details.FlashcardsRepository
import com.raulp.cardshuffler.compose.core.model.FlashcardInfo
import com.raulp.cardshuffler.compose.core.viewmodel.BaseViewModel
import com.raulp.cardshuffler.compose.core.viewmodel.ViewModelStateFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val flashcardsRepository: FlashcardsRepository,
    savedStateHandle: SavedStateHandle,
) : BaseViewModel() {

    internal val uiState: ViewModelStateFlow<DetailsUiState> =
        viewModelStateFlow(DetailsUiState.Loading)

    private val _flashcardInfo = MutableStateFlow<FlashcardInfo?>(null)
    val flashcardInfo = _flashcardInfo.asStateFlow()

    private val _sessionCorrectAnswers = MutableStateFlow(0)
    val sessionCorrectAnswers = _sessionCorrectAnswers.asStateFlow()

    private val _sessionTotalAnswers = MutableStateFlow(0)
    val sessionTotalAnswers = _sessionTotalAnswers.asStateFlow()

    private val _sessionAnswers = MutableStateFlow<List<Boolean>>(emptyList())
    val sessionAnswers = _sessionAnswers.asStateFlow()

    private var flashcards: MutableList<FlashcardInfo> = mutableListOf()
    private var currentIndex = 0

    init {
        val subjectId: String = savedStateHandle.get<String>("subjectId") ?: "data-structures"
        viewModelScope.launch {
            try {
                val result = flashcardsRepository.fetchFlashcardsBySubject(subjectId).first().take(1)
                if (result.isNotEmpty()) {
                    flashcards = result.toMutableList()
                    flashcards.shuffle()
                    getNextRandomFlashcard()
                    uiState.value = DetailsUiState.Idle
                } else {
                    uiState.value = DetailsUiState.Error("No flashcards found for this subject.")
                }
            } catch (e: Exception) {
                uiState.value = DetailsUiState.Error(e.message)
            }
        }
    }

    fun onKnowClick() {
        _sessionCorrectAnswers.value++
        _sessionTotalAnswers.value++
        _sessionAnswers.value = _sessionAnswers.value + true
        updateCard(isCorrect = true)
        getNextRandomFlashcard()
    }

    fun onDoNotKnowClick() {
        _sessionTotalAnswers.value++
        _sessionAnswers.value = _sessionAnswers.value + false
        updateCard(isCorrect = false)
        getNextRandomFlashcard()
    }

    private fun updateCard(isCorrect: Boolean) {
        viewModelScope.launch {
            _flashcardInfo.value?.let { currentCard ->
                val updatedCard = currentCard.addAnswer(isCorrect)
                val cardIndex = flashcards.indexOfFirst { it.id == currentCard.id }
                if (cardIndex != -1) {
                    flashcards[cardIndex] = updatedCard
                }
                _flashcardInfo.value = updatedCard
                flashcardsRepository.updateFlashcard(updatedCard)
            }
        }
    }

    private fun getNextRandomFlashcard() {
        if (flashcards.isNotEmpty()) {
            if (currentIndex >= flashcards.size) {
                currentIndex = 0
                flashcards.shuffle()
            }
            _flashcardInfo.value = flashcards[currentIndex]
            currentIndex++
        }
    }
}

@Stable
internal sealed interface DetailsUiState {

    data object Idle : DetailsUiState

    data object Loading : DetailsUiState

    data class Error(val message: String?) : DetailsUiState
}
