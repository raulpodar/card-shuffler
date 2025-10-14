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

import androidx.test.espresso.IdlingResource
import okhttp3.mockwebserver.MockWebServer

/**
 * An [IdlingResource] to synchronize Espresso with [MockWebServer].
 * This resource is idle when the MockWebServer has no open requests.
 * It is intended for API calls, not for image loading operations with libraries like Glide,
 * which have their own idling resource mechanisms.
 */
class MockWebServerIdlingResource(private val mockWebServer: MockWebServer) : IdlingResource {

    @Volatile
    private var resourceCallback: IdlingResource.ResourceCallback? = null

    override fun getName(): String = "MockWebServerIdlingResource"

    override fun isIdleNow(): Boolean {
        // The server is idle if it has no open requests.
        val idle = mockWebServer.requestCount == 0
        if (idle) {
            resourceCallback?.onTransitionToIdle()
        }
        return idle
    }

    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback?) {
        this.resourceCallback = callback
    }
}
