package com.ilharper.sym.channellist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.ilharper.sym.contact.ContactViewModelBase
import com.ilharper.sym.msf.RetrofitService
import com.ilharper.symri.entity.ext.resource.SymriContact
import com.ilharper.symri.entity.paging.Page
import com.ilharper.symri.entity.payload.ListChannelPayload
import com.ilharper.symri.entity.resource.SatoriChannel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import org.apache.commons.lang3.SerializationUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@HiltViewModel(
    assistedFactory = ChannelListViewModelFactory::class,
)
class ChannelListViewModel
    @AssistedInject
    constructor(
        @Assisted private val contact: SymriContact,
        private val state: SavedStateHandle,
        private val retrofitService: RetrofitService,
    ) : ViewModel(), ContactViewModelBase {
        override val contacts = MutableLiveData<List<SymriContact>>()

        init {
            refresh()
        }

        override fun refresh(force: Boolean) {
            if (contacts.value?.isNotEmpty() == true && !force) return

            if (!retrofitService.tryEnsure()) return

            retrofitService.satoriService!!.listChannel(
                contact.platform!!,
                contact.logins!![0].selfId!!,
                ListChannelPayload(
                    contact.id,
                    null,
                ),
            ).enqueue(
                object :
                    Callback<Page<SatoriChannel>> {
                    override fun onResponse(
                        call: Call<Page<SatoriChannel>>,
                        response: Response<Page<SatoriChannel>>,
                    ) {
                        if (!response.isSuccessful) return
                        contacts.value =
                            response.body()?.data?.map {
                                SymriContact(
                                    contact.logins!!.map(SerializationUtils::clone),
                                    it.id,
                                    it.name,
                                    contact.avatar,
                                    contact.platform,
                                    it.type,
                                )
                            }
                    }

                    override fun onFailure(
                        call: Call<Page<SatoriChannel>>,
                        t: Throwable,
                    ) {
                    }
                },
            )
        }
    }

@AssistedFactory
interface ChannelListViewModelFactory {
    fun create(contact: SymriContact): ChannelListViewModel
}
