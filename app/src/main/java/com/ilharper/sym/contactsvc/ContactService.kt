package com.ilharper.sym.contactsvc

import androidx.lifecycle.MutableLiveData
import com.ilharper.sym.msf.RetrofitService
import com.ilharper.symri.entity.ext.resource.SymriContact
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ContactService
    @Inject
    constructor(
        private val retrofitService: RetrofitService,
    ) {
        val contacts = MutableLiveData<List<SymriContact>>()

        fun refresh() {
            if (!retrofitService.tryEnsure()) return

            retrofitService.symriService!!.listContact().enqueue(
                object :
                    Callback<List<SymriContact>> {
                    override fun onResponse(
                        call: Call<List<SymriContact>>,
                        response: Response<List<SymriContact>>,
                    ) {
                        if (!response.isSuccessful) return
                        contacts.value = response.body()
                    }

                    override fun onFailure(
                        call: Call<List<SymriContact>>,
                        t: Throwable,
                    ) {
                    }
                },
            )
        }
    }
