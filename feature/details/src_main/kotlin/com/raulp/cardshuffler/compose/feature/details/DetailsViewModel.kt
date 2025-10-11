
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
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
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
  val flashcardInfo: StateFlow<FlashcardInfo?> = _flashcardInfo

  private var flashcards: List<FlashcardInfo> = emptyList()
  private var currentFlashcardIndex = 0

  init {
    viewModelScope.launch {
      flashcardsRepository.fetchFlashcardsBySubject(
        subjectId = "all", // You can change this to the desired subjectId
        onComplete = { uiState.tryEmit(key, DetailsUiState.Idle) },
        onError = { uiState.tryEmit(key, DetailsUiState.Error(it)) },
      ).collect {
        flashcards = it
        if (flashcards.isNotEmpty()) {
          _flashcardInfo.value = flashcards[currentFlashcardIndex]
        }
      }
    }
  }

  fun onKnowClick() {
    showNextQuestion()
  }

  fun onDoNotKnowClick() {
    showNextQuestion()
  }

  private fun showNextQuestion() {
    if (flashcards.isNotEmpty()) {
      currentFlashcardIndex = (currentFlashcardIndex + 1) % flashcards.size
      _flashcardInfo.value = flashcards[currentFlashcardIndex]
    }
  }
}

@Stable
internal sealed interface DetailsUiState {

  data object Idle : DetailsUiState

  data object Loading : DetailsUiState

  data class Error(val message: String?) : DetailsUiState
}
