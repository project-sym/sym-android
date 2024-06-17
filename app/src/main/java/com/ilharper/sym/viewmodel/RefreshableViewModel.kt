package com.ilharper.sym.viewmodel

interface RefreshableViewModel {
    fun refresh(force: Boolean = false)
}
