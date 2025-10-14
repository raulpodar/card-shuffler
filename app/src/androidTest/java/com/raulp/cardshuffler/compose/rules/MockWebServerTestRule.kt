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

package com.raulp.cardshuffler.compose.rules

import androidx.test.espresso.IdlingRegistry
import androidx.test.platform.app.InstrumentationRegistry
import com.raulp.cardshuffler.compose.idling.MockWebServerIdlingResource
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.runner.Description
import java.io.InputStreamReader

/**
 * A JUnit [TestRule] that starts a [MockWebServer] before a test and shuts it down after.
 * This rule uses the [@RequiresMockResponse] annotation to enqueue mock API responses and
 * registers a [MockWebServerIdlingResource] to synchronize tests with the server.
 */
class MockWebServerTestRule : CardShufflerBaseTestRule() {

    val mockWebServer = MockWebServer()
    private val idlingResource = MockWebServerIdlingResource(mockWebServer)

    override fun starting(description: Description) {
        IdlingRegistry.getInstance().register(idlingResource)
        mockWebServer.start()
        description.getAnnotation(RequiresMockResponse::class.java)?.let {
            enqueueMockResponses(it.responses)
        }
    }

    override fun finished(description: Description) {
        mockWebServer.shutdown()
        IdlingRegistry.getInstance().unregister(idlingResource)
    }

    private fun enqueueMockResponses(responses: Array<LocalMockResponse>) {
        // The dispatcher serves responses based on the request path.
        mockWebServer.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                // Find the first mock response whose file name is contained in the request path.
                val response = responses.firstOrNull {
                    val mockResponseFileName = it.filePath.substringAfter("mock-responses/").removeSuffix(".json")
                    request.path?.contains(mockResponseFileName, ignoreCase = true) ?: false
                }

                return if (response != null) {
                    val body = InputStreamReader(InstrumentationRegistry.getInstrumentation().context.assets.open(response.filePath)).use { it.readText() }
                    MockResponse()
                        .setResponseCode(response.statusCode)
                        .apply { response.headers.forEach { addHeader(it.key, it.value) } }
                        .setBody(body)
                } else {
                    // Return a 404 Not Found response if no matching mock response is found.
                    MockResponse().setResponseCode(404)
                }
            }
        }
    }
}
