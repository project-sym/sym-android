package com.ilharper.symri.moshi

import com.ilharper.symri.entity.SatoriOp
import com.ilharper.symri.entity.SatoriPacket
import com.ilharper.symri.entity.SatoriPacketEvent
import com.ilharper.symri.entity.SatoriPacketPong
import com.ilharper.symri.entity.SatoriPacketReady
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonAdapter.Factory
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonReader.Options
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.Moshi
import com.squareup.moshi.rawType
import java.lang.reflect.Type

class SatoriPacketJsonAdapterFactory : Factory {
    override fun create(
        type: Type,
        annotations: Set<Annotation>,
        moshi: Moshi,
    ): JsonAdapter<*>? {
        if (type.rawType != SatoriPacket::class.java || annotations.isNotEmpty()) {
            return null
        }
        return SatoriPacketJsonAdapter(
            moshi.adapter(SatoriPacketEvent::class.java),
            moshi.adapter(SatoriPacketPong::class.java),
            moshi.adapter(SatoriPacketReady::class.java),
        )
            .nullSafe()
    }
}

class SatoriPacketJsonAdapter(
    private val satoriPacketEventJsonAdapter: JsonAdapter<SatoriPacketEvent>,
    private val satoriPacketPongJsonAdapter: JsonAdapter<SatoriPacketPong>,
    private val satoriPacketReadyJsonAdapter: JsonAdapter<SatoriPacketReady>,
) : JsonAdapter<SatoriPacket>() {
    override fun fromJson(reader: JsonReader): SatoriPacket? {
        reader.setFailOnUnknown(false)
        val peeked = reader.peekJson()
        // peeked.failOnUnknown = false
        return peeked.use(::getAdapter).fromJson(reader)
    }

    private fun getAdapter(reader: JsonReader): JsonAdapter<out SatoriPacket> {
        reader.beginObject()
        while (reader.hasNext()) {
            if (reader.selectName(Options.of("op")) == -1) {
                reader.skipName()
                reader.skipValue()
                continue
            }
            return when (reader.nextInt()) {
                SatoriOp.Event.value -> satoriPacketEventJsonAdapter
                SatoriOp.Pong.value -> satoriPacketPongJsonAdapter
                SatoriOp.Ready.value -> satoriPacketReadyJsonAdapter
                else -> throw JsonDataException(
                    "Expected SatoriOp but found '${reader.nextString()}'.",
                )
            }
        }
        throw JsonDataException("Missing label for op")
    }

    override fun toJson(
        writer: JsonWriter,
        value: SatoriPacket?,
    ) {
        throw NotImplementedError()
    }
}
