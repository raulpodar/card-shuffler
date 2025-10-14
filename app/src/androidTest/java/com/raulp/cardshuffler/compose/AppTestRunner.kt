package com.raulp.cardshuffler.compose

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import dagger.hilt.android.testing.HiltTestApplication

/**
 * A custom test instrumentation runner for Hilt.
 * This runner specifies [HiltTestApplication] as the test application class.
 */
@Suppress("unused")
class AppTestRunner : AndroidJUnitRunner() {
  override fun newApplication(
    cl: ClassLoader?,
    className: String?,
    context: Context?,
  ): Application = super.newApplication(cl, HiltTestApplication::class.java.name, context)
}
