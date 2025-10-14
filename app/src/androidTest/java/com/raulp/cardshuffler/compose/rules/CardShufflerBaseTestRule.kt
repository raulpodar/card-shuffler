

package com.raulp.cardshuffler.compose.rules

import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

/**
 * An abstract base class for [TestRule]s in the "CardShuffler" project.
 * This class provides a framework for applying setup/teardown logic based on test method annotations.
 */
abstract class CardShufflerBaseTestRule : TestRule {

  override fun apply(base: Statement, description: Description): Statement = object : Statement() {
    override fun evaluate() {
      try {
        starting(description)
        base.evaluate()
      } finally {
        finished(description)
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
