package com.ilharper.symri.moshi

import com.ilharper.symri.entity.SatoriEvent
import com.ilharper.symri.entity.SatoriEventBase
import com.ilharper.symri.entity.SatoriOp
import com.ilharper.symri.entity.SatoriPong
import com.ilharper.symri.entity.resource.SatoriReady
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonAdapter.Factory
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonReader.Options
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.Moshi
import com.squareup.moshi.rawType
import java.lang.reflect.Type

class SatoriEventJsonAdapterFactory : Factory {
    override fun create(
        type: Type,
        annotations: Set<Annotation>,
        moshi: Moshi,
    ): JsonAdapter<*>? {
        if (type.rawType != SatoriEventBase::class.java || annotations.isNotEmpty()) {
            return null
        }
        return SatoriEventJsonAdapter(
            moshi.adapter(SatoriEvent::class.java),
            moshi.adapter(SatoriPong::class.java),
            moshi.adapter(SatoriReady::class.java),
        )
            .nullSafe()
    }
}

class SatoriEventJsonAdapter(
    private val satoriEventJsonAdapter: JsonAdapter<SatoriEvent>,
    private val satoriPongJsonAdapter: JsonAdapter<SatoriPong>,
    private val satoriReadyJsonAdapter: JsonAdapter<SatoriReady>,
) : JsonAdapter<SatoriEventBase>() {
    override fun fromJson(reader: JsonReader): SatoriEventBase? {
        reader.setFailOnUnknown(false)
        val peeked = reader.peekJson()
        // peeked.failOnUnknown = false
        return peeked.use(::getAdapter).fromJson(reader)
    }

    private fun getAdapter(reader: JsonReader): JsonAdapter<out SatoriEventBase> {
        reader.beginObject()
        while (reader.hasNext()) {
            if (reader.selectName(Options.of("op")) == -1) {
                reader.skipName()
                reader.skipValue()
                continue
            }
            return when (reader.nextInt()) {
                SatoriOp.Event.value -> satoriEventJsonAdapter
                SatoriOp.Pong.value -> satoriPongJsonAdapter
                SatoriOp.Ready.value -> satoriReadyJsonAdapter
                else -> throw JsonDataException(
                    "Expected SatoriOp but found '${reader.nextString()}'.",
                )
            }
        }
        throw JsonDataException("Missing label for op")
    }

    override fun toJson(
        writer: JsonWriter,
        value: SatoriEventBase?,
    ) {
        throw NotImplementedError()
    }
}
