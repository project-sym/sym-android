package com.ilharper.sym.image

import android.content.Context
import android.os.Build
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.decode.SvgDecoder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient

@Module
@InstallIn(SingletonComponent::class)
class CoilModule {
    @Provides
    fun provideCoil(
        @ApplicationContext context: Context,
        httpClient: OkHttpClient,
    ) = ImageLoader
        .Builder(context)
        .components {
            if (Build.VERSION.SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .components {
            add(SvgDecoder.Factory())
        }
        .okHttpClient(httpClient)
        .build()
}
