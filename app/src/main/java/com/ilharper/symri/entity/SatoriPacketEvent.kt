package com.ilharper.symri.entity

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
@JsonClass(generateAdapter = true)
class SatoriPacketEvent(
    var body: SatoriEvent? = null,
    override var op: Int = SatoriOp.Event.value,
) : SatoriPacket, Serializable, Parcelable
