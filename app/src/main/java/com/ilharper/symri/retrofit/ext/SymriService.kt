package com.ilharper.symri.retrofit.ext

import com.ilharper.symri.entity.ext.resource.SymriContact
import retrofit2.Call
import retrofit2.http.POST

interface SymriService {
    @POST("contact.list")
    fun listContact(): Call<List<SymriContact>>
}
