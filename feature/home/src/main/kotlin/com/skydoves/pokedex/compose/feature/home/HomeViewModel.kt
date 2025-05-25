

package com.raulp.cardshuffler.compose.feature.home

import androidx.compose.runtime.Stable
import androidx.lifecycle.viewModelScope
import com.raulp.cardshuffler.compose.core.data.repository.home.HomeRepository
import com.raulp.cardshuffler.compose.core.model.Pokemon
import com.raulp.cardshuffler.compose.core.viewmodel.BaseViewModel
import com.raulp.cardshuffler.compose.core.viewmodel.ViewModelStateFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
  private val homeRepository: HomeRepository,
) : BaseViewModel() {

  internal val uiState: ViewModelStateFlow<HomeUiState> = viewModelStateFlow(HomeUiState.Loading)

  private val pokemonFetchingIndex: MutableStateFlow<Int> = MutableStateFlow(0)
  val pokemonList: StateFlow<List<Pokemon>> = pokemonFetchingIndex.flatMapLatest { page ->
    homeRepository.fetchPokemonList(
      page = page,
      onStart = { uiState.tryEmit(key, HomeUiState.Loading) },
      onComplete = { uiState.tryEmit(key, HomeUiState.Idle) },
      onError = { uiState.tryEmit(key, HomeUiState.Error(it)) },
    )
  }.stateIn(
    scope = viewModelScope,
    started = SharingStarted.WhileSubscribed(5_000),
    initialValue = emptyList(),
  )

  fun fetchNextPokemonList() {
    if (uiState.value != HomeUiState.Loading) {
      pokemonFetchingIndex.value++
    }
  }
}

@Stable
internal sealed interface HomeUiState {

  data object Idle : HomeUiState

  data object Loading : HomeUiState

  data class Error(val message: String?) : HomeUiState
}
