package com.ilharper.sym.msf

import com.ilharper.symri.entity.SatoriOp
import com.ilharper.symri.entity.resource.SatoriChannelType
import com.ilharper.symri.moshi.createEnumJsonAdapter
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
            .build()
}
