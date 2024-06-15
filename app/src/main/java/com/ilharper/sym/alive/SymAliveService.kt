package com.ilharper.sym.alive

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.ilharper.sym.msf.Msf
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SymAliveService : Service() {
    @Inject
    lateinit var msf: Msf

    override fun onBind(intent: Intent?): IBinder? {
        return null // As doc request
    }

    override fun onStartCommand(
        intent: Intent?,
        flags: Int,
        startId: Int,
    ): Int {
        msf.tryEnsure()

        return START_STICKY
    }

    override fun onDestroy() {
        startService(Intent(this, SymAliveService::class.java))
    }
}
