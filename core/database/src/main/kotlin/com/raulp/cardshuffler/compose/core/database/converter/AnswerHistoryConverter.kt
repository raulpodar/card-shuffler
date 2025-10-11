

package com.raulp.cardshuffler.compose.core.database.converter

import androidx.room.TypeConverter

object AnswerHistoryConverter {
  @TypeConverter
  fun fromString(value: String?): List<Boolean> =
    value?.split(",")?.map { it.toBoolean() } ?: emptyList()

  @TypeConverter
  fun fromList(list: List<Boolean>?): String = list?.joinToString(",") ?: ""
}
