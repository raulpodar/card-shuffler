

package com.raulp.cardshuffler.compose.core.network

import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME

@Qualifier
@Retention(RUNTIME)
annotation class Dispatcher(val cardShufflerAppDispatchers: CardShufflerAppDispatchers)

enum class CardShufflerAppDispatchers {
  IO,
}
