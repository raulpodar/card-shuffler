

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
  val headers: Map<String, String> = mapOf("Content-Type" to "application/json"),
) {
  SUBJECTS_SUCCESS("mock-responses/subjects.json"),
  FLASHCARDS_SUCCESS("mock-responses/flashcards.json"),
  // Add more mock responses here
}
