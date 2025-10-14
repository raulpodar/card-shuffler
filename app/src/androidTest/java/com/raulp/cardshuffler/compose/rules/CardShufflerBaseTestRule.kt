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
 * distributed under the License is in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.raulp.cardshuffler.compose.rules

import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

/**
 * An abstract base class for [TestRule]s in the "CardShuffler" project.
 * This class provides a framework for applying setup/teardown logic based on test method annotations.
 */
abstract class CardShufflerBaseTestRule : TestRule {

    override fun apply(base: Statement, description: Description): Statement {
        return object : Statement() {
            override fun evaluate() {
                try {
                    starting(description)
                    base.evaluate()
                } finally {
                    finished(description)
                }
            }
        }
    }

    protected open fun starting(description: Description) {
        // No-op by default
    }

    protected open fun finished(description: Description) {
        // No-op by default
    }
}
