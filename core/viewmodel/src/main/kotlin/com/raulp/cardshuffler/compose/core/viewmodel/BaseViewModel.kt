

package com.raulp.cardshuffler.compose.core.viewmodel

import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

  protected val key: ViewModelKey = ViewModelKey(this::class.java.name)

  protected fun <T> BaseViewModel.viewModelStateFlow(value: T): ViewModelStateFlow<T> =
    ViewModelStateFlow(key = key, value = value)
}
