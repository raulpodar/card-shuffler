package com.raulp.cardshuffler.compose.core.database.entitiy

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AndroidTopicEntity(var page: Int = 0, @PrimaryKey val name: String, val url: String)
