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

package com.raulp.cardshuffler.compose

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.raulp.cardshuffler.compose.pom.BasePage
import com.raulp.cardshuffler.compose.rules.LocalMockResponse
import com.raulp.cardshuffler.compose.rules.MockWebServerTestRule
import com.raulp.cardshuffler.compose.rules.RequiresMockResponse
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

    @get:Rule(order = 2)
    val mockWebServerTestRule = MockWebServerTestRule()

    private val detailsPage = object : BasePage<MainActivity>(composeTestRule) {}

    @Test
    @RequiresMockResponse([LocalMockResponse.SUBJECTS_SUCCESS, LocalMockResponse.FLASHCARDS_SUCCESS])
    fun testDetailsPage_launchesSuccessfully() {
        // Wait for images on the homepage to load (optional, for visual inspection)
        Thread.sleep(1000)

        // Click on the first Subject card
        detailsPage.clickFirstNodeWithTag("Subject")

        // Assert that the details screen is displayed
        detailsPage.assertNodeIsDisplayed("CardShufflerDetails")

        // Wait for the details page to settle
        Thread.sleep(1000)

        // Assert that the question is displayed and print it
        detailsPage.assertNodeIsDisplayed("Question")
        println("Question displayed: What is an Array?")
    }
}
