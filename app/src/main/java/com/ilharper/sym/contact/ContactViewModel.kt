package com.ilharper.sym.contact

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.ilharper.sym.repository.contact.ContactRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ContactViewModel
    @Inject
    constructor(
        private val state: SavedStateHandle,
        private val contactRepository: ContactRepository,
    ) : ViewModel() {
        val contacts = contactRepository.contacts

        fun refresh(force: Boolean = false) {
            if (contacts.value?.isNotEmpty() == true && !force) return
            contactRepository.refresh()
        }
    }
