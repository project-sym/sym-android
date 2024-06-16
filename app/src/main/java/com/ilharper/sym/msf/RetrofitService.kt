package com.ilharper.sym.msf

import com.ilharper.sym.settings.SymSettings
import com.ilharper.symri.retrofit.SatoriAdminService
import com.ilharper.symri.retrofit.SatoriService
import com.ilharper.symri.retrofit.ext.SymriService
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RetrofitService
    @Inject
    constructor(
        private val symSettings: SymSettings,
        private val httpClient: OkHttpClient,
        private val moshi: Moshi,
    ) {
        var satoriService: SatoriService? = null
        var satoriAdminService: SatoriAdminService? = null
        var symriService: SymriService? = null

        fun tryEnsure(): Boolean {
            if (satoriService != null) return true

            if (!symSettings.checkLoaded()) return false

            val converterFactory = MoshiConverterFactory.create(moshi)

            satoriService =
                Retrofit
                    .Builder()
                    .baseUrl("${symSettings.url}${symSettings.satoriPrefix}/v1/")
                    .client(httpClient)
                    .addConverterFactory(converterFactory)
                    .build()
                    .create(SatoriService::class.java)

            satoriAdminService =
                Retrofit
                    .Builder()
                    .baseUrl("${symSettings.url}${symSettings.satoriPrefix}/v1/admin/")
                    .client(httpClient)
                    .addConverterFactory(converterFactory)
                    .build()
                    .create(SatoriAdminService::class.java)

            symriService =
                Retrofit
                    .Builder()
                    .baseUrl("${symSettings.url}${symSettings.symPrefix}/v1/")
                    .client(httpClient)
                    .addConverterFactory(converterFactory)
                    .build()
                    .create(SymriService::class.java)

            return true
        }
    }
