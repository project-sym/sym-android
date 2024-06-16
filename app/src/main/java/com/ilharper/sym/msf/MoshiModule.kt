package com.ilharper.sym.msf

import com.ilharper.symri.entity.SatoriEventBase
import com.ilharper.symri.entity.SatoriIdentify
import com.ilharper.symri.entity.SatoriOp
import com.ilharper.symri.entity.resource.SatoriChannelType
import com.ilharper.symri.moshi.SatoriEventJsonAdapterFactory
import com.ilharper.symri.moshi.createEnumJsonAdapter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class MoshiModule {
    @Provides
    fun provideMoshi(): Moshi =
        Moshi.Builder()
            .add(createEnumJsonAdapter<SatoriChannelType>())
            .add(createEnumJsonAdapter<SatoriOp>())
            .add(SatoriEventJsonAdapterFactory())
            .build()

    @Provides
    fun provideSatoriIdentifyJsonAdapter(moshi: Moshi): JsonAdapter<SatoriIdentify> = moshi.adapter(SatoriIdentify::class.java)

    @Provides
    fun provideSatoriEventBaseJsonAdapter(moshi: Moshi): JsonAdapter<SatoriEventBase> = moshi.adapter(SatoriEventBase::class.java)
}
