/*
 * Copyright 2024 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
