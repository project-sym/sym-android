package com.ilharper.symri.entity.ext.resource

import com.ilharper.symri.entity.SatoriEvent
import org.apache.commons.lang3.SerializationUtils

fun SatoriEvent.toMessage() =
    SerializationUtils.clone(this).let { event ->
        event.message?.apply {
            channel = event.channel
            member = event.member
            guild = event.guild
            user = event.user
        }
    }
