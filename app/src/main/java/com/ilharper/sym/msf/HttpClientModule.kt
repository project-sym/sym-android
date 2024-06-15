package com.ilharper.sym.msf

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
class HttpClientModule {
    @Provides
    fun provideHttpClient(): OkHttpClient =
        OkHttpClient
            .Builder()
            .pingInterval(5, TimeUnit.SECONDS)
            .build()

    @Provides
    fun provideMoshi(): Moshi =
        Moshi.Builder()
            .build()
}
