package com.ilharper.symri.entity

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
@JsonClass(generateAdapter = true)
class SatoriPacketIdentify(
    var body: SatoriIdentify? = null,
    override var op: Int = SatoriOp.Identify.value,
) : SatoriPacket, Serializable, Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
class SatoriIdentify(
    var token: String? = null,
    var sequence: Int? = null,
) : Serializable, Parcelable
