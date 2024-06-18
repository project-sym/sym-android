package com.ilharper.symri.entity.payload

import android.os.Parcelable
import com.ilharper.symri.element.SatoriElement
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
@JsonClass(generateAdapter = true)
class CreateMessagePayload(
    @Json(name = "channel_id")
    var channelId: String? = null,
    var content: List<SatoriElement>? = null,
) : Serializable, Parcelable
