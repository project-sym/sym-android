package com.ilharper.sym.data

import com.ilharper.symri.entity.SatoriEventBase
import com.ilharper.symri.entity.SatoriIdentify
import com.ilharper.symri.entity.SatoriOp
import com.ilharper.symri.entity.ext.resource.SymriContact
import com.ilharper.symri.entity.paging.Page
import com.ilharper.symri.entity.resource.SatoriChannel
import com.ilharper.symri.entity.resource.SatoriChannelType
import com.ilharper.symri.moshi.SatoriEventJsonAdapterFactory
import com.ilharper.symri.moshi.createEnumJsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import com.squareup.moshi.addAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@OptIn(ExperimentalStdlibApi::class)
@Module
@InstallIn(SingletonComponent::class)
class MoshiModule {
    @Provides
    fun provideMoshi(): Moshi =
        Moshi.Builder()
            .addAdapter(createEnumJsonAdapter<SatoriChannelType>())
            .addAdapter(createEnumJsonAdapter<SatoriOp>())
            .add(SatoriEventJsonAdapterFactory())
            .build()

    @Provides
    fun provideSatoriIdentifyJsonAdapter(moshi: Moshi) = moshi.adapter<SatoriIdentify>()

    @Provides
    fun provideSatoriEventBaseJsonAdapter(moshi: Moshi) = moshi.adapter<SatoriEventBase>()

    @Provides
    fun provideSymriContactJsonAdapter(moshi: Moshi) = moshi.adapter<SymriContact>()

    @Provides
    fun providePageSatoriChannelJsonAdapter(moshi: Moshi) = moshi.adapter<Page<SatoriChannel>>()
}
