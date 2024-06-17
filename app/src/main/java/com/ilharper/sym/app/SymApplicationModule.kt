package com.ilharper.sym.app

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class SymApplicationModule {
    @Provides
    fun provideSymApplication(
        @ApplicationContext context: Context,
    ) = context as SymApplication
}
