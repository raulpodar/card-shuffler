package com.example.flippingflashcard // Replace with your package name

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun OutlinedCardExample() {
  var expanded by remember { mutableStateOf(false) }

  OutlinedCard(
    colors = CardDefaults.cardColors(
      containerColor = MaterialTheme.colorScheme.surface,
    ),
    elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
    modifier = Modifier
      .padding(16.dp) // Add some outer padding
      .fillMaxWidth() // Let the card take up the available width
      .animateContentSize() // This is the key for smooth expansion
      .clickable { expanded = !expanded }
//      .animateContentSize(
////      // HERE we use the values from our theme's MotionScheme!
////      animationSpec = tween(
////        fancySlideTransition(true, 1000, 600)
////      )
//      )
  ) {
    Column(
      // Apply padding to the content inside the card
      modifier = Modifier.padding(16.dp)
    ) {
      Text(
        text = "Question",
        style = MaterialTheme.typography.titleMedium
      )

      HorizontalDivider(
        modifier = Modifier.padding(vertical = 8.dp) // Add space around the divider
      )
      Text("Answer")
      if (expanded) {
        Text(
          text = "Content Sample for Display on Expansion of Card. This text is now visible because the card has expanded.",
          style = MaterialTheme.typography.bodyMedium
        )
      }
    }
  }
}