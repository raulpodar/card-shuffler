

package com.raulp.cardshuffler.compose.core.navigation

import androidx.compose.animation.core.tween
import androidx.compose.ui.geometry.Rect

val boundsTransform = { _: Rect, _: Rect -> tween<Rect>(550) }
