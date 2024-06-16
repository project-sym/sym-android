package com.ilharper.sym.contact

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.ilharper.sym.contactsvc.ContactService
import com.ilharper.symri.entity.ext.resource.SymriContact
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ContactViewModel
    @Inject
    constructor(
        private val state: SavedStateHandle,
        private val contactService: ContactService,
    ) : ViewModel() {
        val contacts = MutableLiveData<List<SymriContact>>()

        init {
            contactService.contacts.observeForever {
                contacts.value = it
            }
        }

        fun refresh(force: Boolean = false) {
            if (contacts.value?.isNotEmpty() == true && !force) return
            contactService.refresh()
        }
    }
