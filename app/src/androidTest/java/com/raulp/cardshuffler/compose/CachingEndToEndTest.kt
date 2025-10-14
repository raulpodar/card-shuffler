

package com.raulp.cardshuffler.compose

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.raulp.cardshuffler.compose.core.database.CardShufflerDatabase
import com.raulp.cardshuffler.compose.pom.BasePage
import com.raulp.cardshuffler.compose.rules.LocalMockResponse
import com.raulp.cardshuffler.compose.rules.MockWebServerTestRule
import com.raulp.cardshuffler.compose.rules.RequiresMockResponse
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class CachingEndToEndTest {

  @get:Rule(order = 0)
  val hiltRule = HiltAndroidRule(this)

  @get:Rule(order = 1)
  val composeTestRule = createAndroidComposeRule<MainActivity>()

  @get:Rule(order = 2)
  val mockWebServerTestRule = MockWebServerTestRule()

  @Inject
  lateinit var database: CardShufflerDatabase

  private val homePage = object : BasePage<MainActivity>(composeTestRule) {}

  @Before
  fun setUp() {
    hiltRule.inject()
    // Clear the database before each test to ensure a clean slate
    runBlocking {
      database.clearAllTables()
    }
  }

  @Test
  @RequiresMockResponse([LocalMockResponse.SUBJECTS_SUCCESS])
  fun testDataIsFetchedFromNetworkAndCached() {
    // Phase 1: First launch - fetch from network
    // The MockWebServerIdlingResource will wait for the request to complete
    val initialRequestCount = mockWebServerTestRule.mockWebServer.requestCount
    homePage.assertNodeIsDisplayed("Subject")
    assertThat(mockWebServerTestRule.mockWebServer.requestCount).isEqualTo(
      initialRequestCount + 1,
    )

    // Phase 2: Second launch (simulated by navigating back) - load from cache
    composeTestRule.activityRule.scenario.onActivity { it.onBackPressed() }
    homePage.clickFirstNodeWithTag("Subject") // This is just to simulate user interaction

    // Verify that no new network request was made
    assertThat(mockWebServerTestRule.mockWebServer.requestCount).isEqualTo(
      initialRequestCount + 1,
    )
  }
}
