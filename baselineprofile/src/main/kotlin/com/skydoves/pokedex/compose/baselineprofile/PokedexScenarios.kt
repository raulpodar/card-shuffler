package com.raulp.cardshuffler.compose.baselineprofile

import androidx.benchmark.macro.MacrobenchmarkScope
import androidx.test.uiautomator.By
import androidx.test.uiautomator.BySelector
import androidx.test.uiautomator.Direction
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObject2
import androidx.test.uiautomator.Until

fun MacrobenchmarkScope.CardShufflerScenarios() {
  // -----------------
  // CardShuffler Home
  // -----------------
  exploreCardShufflerHome()
  navigateFromHomeToDetails()

  // -----------------
  // CardShuffler Details
  // -----------------
  detailsWaitForContent()
}

fun MacrobenchmarkScope.exploreCardShufflerHome() = device.apply {
  homeWaitForContent()
  CardShufflerListScrollDownUp()
}

fun MacrobenchmarkScope.homeWaitForContent() = device.apply {
  wait(Until.hasObject(By.res("CardShufflerList")), 15_000L)
}

fun MacrobenchmarkScope.CardShufflerListScrollDownUp() = device.apply {
  val channelList = waitAndFindObject(By.res("CardShufflerList"), 15_000L)
  flingElementDownUp(channelList)
}

fun MacrobenchmarkScope.navigateFromHomeToDetails() = device.apply {
  waitAndFindObject(By.res("Pokemon"), 15_000L).click()
  waitForIdle()
}

fun MacrobenchmarkScope.detailsWaitForContent() = device.apply {
  wait(Until.hasObject(By.res("CardShufflerDetails")), 15_000L)
}

internal fun UiDevice.flingElementDownUp(element: UiObject2) {
  // Set some margin from the sides to prevent triggering system navigation
  element.setGestureMargin(displayWidth / 5)

  element.fling(Direction.DOWN)
  waitForIdle()
  element.fling(Direction.UP)
}

/**
 * Waits until an object with [selector] if visible on screen and returns the object.
 * If the element is not available in [timeout], throws [AssertionError]
 */
internal fun UiDevice.waitAndFindObject(selector: BySelector, timeout: Long = 15_000L): UiObject2 {
  if (!wait(Until.hasObject(selector), timeout)) {
    throw AssertionError("Element not found on screen in ${timeout}ms (selector=$selector)")
  }

  return findObject(selector)
}
