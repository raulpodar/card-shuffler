

package com.raulp.cardshuffler.compose.core.database.entitiy

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TopicEntity(@PrimaryKey val id: String, val url: String, val title: String)
