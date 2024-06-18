package com.ilharper.symri.entity

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
@JsonClass(generateAdapter = true)
class SatoriPacketPong(
    override var op: Int = SatoriOp.Pong.value,
) : SatoriPacket, Serializable, Parcelable
