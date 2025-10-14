

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
