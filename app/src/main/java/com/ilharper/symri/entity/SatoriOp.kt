package com.ilharper.symri.entity

import com.ilharper.symri.moshi.IEnumValue

enum class SatoriOp(override val value: Int) : IEnumValue {
    Event(0),
    Ping(1),
    Pong(2),
    Identify(3),
    Ready(4),
}
