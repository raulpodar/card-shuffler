

package com.raulp.cardshuffler.compose

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.raulp.cardshuffler.compose.pom.BasePage
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class CardShufflerDetailsLaunchTest {

  @get:Rule(order = 0)
  val hiltRule = HiltAndroidRule(this)

  @get:Rule(order = 1)
  val composeTestRule = createAndroidComposeRule<MainActivity>()

  private val detailsPage = object : BasePage<MainActivity>(composeTestRule) {}

  @Test
  fun testDetailsPage_launchesSuccessfully() {
    Thread.sleep(1000)

    detailsPage.clickFirstNodeWithTag("Subject")

    detailsPage.assertNodeIsDisplayed("CardShufflerDetails")

    Thread.sleep(1000)

    detailsPage.assertNodeIsDisplayed("Question")
    println("Question displayed: What is an Array?")
  }
}
