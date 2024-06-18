package com.ilharper.symri.entity

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
@JsonClass(generateAdapter = true)
class SatoriPacketReady(
    override var op: Int = SatoriOp.Ready.value,
) : SatoriPacket, Serializable, Parcelable
