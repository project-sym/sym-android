package com.ilharper.sym.msf

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Msf
    @Inject
    constructor(
        private val listener: MsfListener,
    ) {
        fun isLoaded() = listener.isLoaded()

        fun tryEnsure() = listener.tryEnsure()

        fun stop() = listener.stop()
    }
