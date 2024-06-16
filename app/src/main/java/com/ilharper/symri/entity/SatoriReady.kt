package com.ilharper.symri.entity

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class SatoriReady : SatoriEventBase {
    override var op: Int = SatoriOp.Ready.value
}
