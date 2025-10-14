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
    protected val composeTestRule: AndroidComposeTestRule<ActivityScenarioRule<A>, A>
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
