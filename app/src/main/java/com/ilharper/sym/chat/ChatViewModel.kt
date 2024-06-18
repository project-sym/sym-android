package com.ilharper.sym.chat

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.ilharper.sym.msf.Msf
import com.ilharper.sym.msf.RetrofitService
import com.ilharper.sym.viewmodel.RefreshableViewModel
import com.ilharper.symri.entity.ext.resource.SymriContact
import com.ilharper.symri.entity.ext.resource.toMessage
import com.ilharper.symri.entity.paging.Page
import com.ilharper.symri.entity.payload.ListMessagePayload
import com.ilharper.symri.entity.resource.SatoriMessage
import com.ilharper.symri.rxjava.ofChannel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@SuppressLint("CheckResult")
@HiltViewModel(
    assistedFactory = ChatViewModelFactory::class,
)
class ChatViewModel
    @AssistedInject
    constructor(
        @Assisted private val contact: SymriContact,
        private val state: SavedStateHandle,
        private val retrofitService: RetrofitService,
        private val msf: Msf,
    ) : ViewModel(), RefreshableViewModel {
        val messages = MutableLiveData<MutableList<SatoriMessage>>(mutableListOf())

        init {
            refresh()

            msf
                .event
                .ofChannel(contact.id!!)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { event ->
                    event.toMessage()?.let { message ->
                        messages.value!!.add(message)
                        notifyMessages()
                    }
                }
        }

        private fun notifyMessages() {
            messages.value = messages.value
        }

        override fun refresh(force: Boolean) {
            if (messages.value!!.isNotEmpty() && !force) return

            if (!retrofitService.tryEnsure()) return

            retrofitService.satoriService!!.listMessage(
                contact.platform!!,
                contact.logins!![0].selfId!!,
                ListMessagePayload(
                    contact.id,
                ),
            ).enqueue(
                object : Callback<Page<SatoriMessage>> {
                    override fun onResponse(
                        call: Call<Page<SatoriMessage>>,
                        response: Response<Page<SatoriMessage>>,
                    ) {
                        if (!response.isSuccessful) return
                        messages.value = response.body()?.data!!.toMutableList()
                    }

                    override fun onFailure(
                        call: Call<Page<SatoriMessage>>,
                        t: Throwable,
                    ) {
                    }
                },
            )
        }
    }

@AssistedFactory
interface ChatViewModelFactory {
    fun create(contact: SymriContact): ChatViewModel
}
