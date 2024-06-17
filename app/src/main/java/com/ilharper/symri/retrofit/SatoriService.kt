package com.ilharper.symri.retrofit

import com.ilharper.symri.entity.paging.Page
import com.ilharper.symri.entity.payload.ListChannelPayload
import com.ilharper.symri.entity.payload.ListMessagePayload
import com.ilharper.symri.entity.resource.SatoriChannel
import com.ilharper.symri.entity.resource.SatoriMessage
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface SatoriService {
    @POST("channel.list")
    fun listChannel(
        @Header("X-Platform") platform: String,
        @Header("X-Self-ID") loginId: String,
        @Body payload: ListChannelPayload,
    ): Call<Page<SatoriChannel>>

    @POST("message.list")
    fun listMessage(
        @Header("X-Platform") platform: String,
        @Header("X-Self-ID") loginId: String,
        @Body payload: ListMessagePayload,
    ): Call<Page<SatoriMessage>>
}
