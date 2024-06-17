package com.ilharper.sym.contact

import androidx.lifecycle.MutableLiveData
import com.ilharper.sym.viewmodel.RefreshableViewModel
import com.ilharper.symri.entity.ext.resource.SymriContact

interface ContactViewModelBase : RefreshableViewModel {
    val contacts: MutableLiveData<List<SymriContact>>
}
