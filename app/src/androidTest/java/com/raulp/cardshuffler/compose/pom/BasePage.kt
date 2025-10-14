

package com.raulp.cardshuffler.compose.pom

import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.rules.ActivityScenarioRule

/**
 * A base class for Page Objects in a Jetpack Compose UI test.
 *
 * @param composeTestRule The [AndroidComposeTestRule] for the test.
 */
abstract class BasePage<A : androidx.activity.ComponentActivity>(
  protected val composeTestRule: AndroidComposeTestRule<ActivityScenarioRule<A>, A>,
) {

  /**
   * Clicks a node with the given test tag.
   */
  fun clickNodeWithTag(testTag: String) {
    composeTestRule.onNodeWithTag(testTag).performClick()
  }

  /**
   * Clicks the first node found with the given test tag.
   */
  fun clickFirstNodeWithTag(testTag: String) {
    composeTestRule.onAllNodesWithTag(testTag, useUnmergedTree = true).onFirst().performClick()
  }

  /**
   * Asserts that a node with the given test tag is displayed.
   */
  fun assertNodeIsDisplayed(testTag: String) {
    composeTestRule.onNodeWithTag(testTag).assertExists()
  }

  /**
   * Types the given text into a node with the given test tag.
   */
  fun typeTextIntoNode(testTag: String, text: String) {
    composeTestRule.onNodeWithTag(testTag).performTextInput(text)
  }
}
