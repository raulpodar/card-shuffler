

package com.raulp.cardshuffler.compose.rules

/**
 * A custom annotation to specify which mock API responses to serve for a test.
 *
 * @see MockWebServerRule
 * @see LocalMockResponse
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
annotation class RequiresMockResponse(val responses: Array<LocalMockResponse>)
