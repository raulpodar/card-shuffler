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

/**
 * Defines mock API responses for use with [MockWebServerRule].
 *
 * @param filePath The path to the JSON file in the assets directory.
 * @param statusCode The HTTP status code to return.
 * @param headers A map of HTTP headers to include in the response.
 */
enum class LocalMockResponse(
    val filePath: String,
    val statusCode: Int = 200,
    val headers: Map<String, String> = mapOf("Content-Type" to "application/json")
) {
    SUBJECTS_SUCCESS("mock-responses/subjects.json"),
    FLASHCARDS_SUCCESS("mock-responses/flashcards.json"),
    // Add more mock responses here
}
