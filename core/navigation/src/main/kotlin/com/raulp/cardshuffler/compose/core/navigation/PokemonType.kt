

package com.raulp.cardshuffler.compose.core.navigation

import android.net.Uri
import android.os.Bundle
import androidx.core.os.BundleCompat
import androidx.navigation.NavType
import com.raulp.cardshuffler.compose.core.model.Topic
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object PokemonType : NavType<Topic>(isNullableAllowed = false) {

  override fun put(bundle: Bundle, key: String, value: Topic) {
    bundle.putParcelable(key, value)
  }

  override fun get(bundle: Bundle, key: String): Topic? =
    BundleCompat.getParcelable(bundle, key, Topic::class.java)

  override fun parseValue(value: String): Topic = Json.decodeFromString(Uri.decode(value))

  override fun serializeAsValue(value: Topic): String = Uri.encode(Json.encodeToString(value))
}
