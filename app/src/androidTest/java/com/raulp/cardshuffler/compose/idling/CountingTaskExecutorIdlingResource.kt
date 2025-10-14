

package com.raulp.cardshuffler.compose.idling

import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.idling.CountingIdlingResource

/**
 * An [IdlingResource] to track asynchronous background tasks.
 * This is useful for synchronizing tests with long-running operations.
 */
object CountingTaskExecutorIdlingResource {

  private const val RESOURCE_NAME = "CountingTaskExecutor"
  private val countingIdlingResource = CountingIdlingResource(RESOURCE_NAME)

  fun increment() {
    countingIdlingResource.increment()
  }

  fun decrement() {
    if (!countingIdlingResource.isIdleNow) {
      countingIdlingResource.decrement()
    }
  }

  fun register() {
    IdlingRegistry.getInstance().register(countingIdlingResource)
  }

  fun unregister() {
    IdlingRegistry.getInstance().unregister(countingIdlingResource)
  }
}
