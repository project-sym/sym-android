package com.ilharper.sym.msf

import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.ilharper.sym.alive.SymAliveService
import com.ilharper.sym.app.SymApplication
import com.ilharper.sym.repository.login.LoginRepository
import com.ilharper.sym.settings.SymSettings
import com.ilharper.symri.entity.SatoriEvent
import com.ilharper.symri.entity.SatoriIdentify
import com.ilharper.symri.entity.SatoriPacket
import com.ilharper.symri.entity.SatoriPacketEvent
import com.ilharper.symri.entity.SatoriPacketIdentify
import com.ilharper.symri.entity.SatoriPacketPong
import com.ilharper.symri.entity.SatoriPacketReady
import com.squareup.moshi.JsonAdapter
import io.reactivex.rxjava3.subjects.PublishSubject
import io.reactivex.rxjava3.subjects.Subject
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okio.ByteString
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MsfListener
    @Inject
    constructor(
        private val app: SymApplication,
        private val symSettings: SymSettings,
        private val httpClient: OkHttpClient,
        private val satoriPacketIdentifyJsonAdapter: JsonAdapter<SatoriPacketIdentify>,
        private val satoriPacketJsonAdapter: JsonAdapter<SatoriPacket>,
        private val loginRepository: LoginRepository,
    ) : WebSocketListener() {
        private var ws: WebSocket? = null

        // Must specify type here to avoid kotlin recognize this as Subject<SatoriEvent!>
        val event: Subject<SatoriEvent> = PublishSubject.create<SatoriEvent>().toSerialized()

        fun isLoaded() = ws != null

        fun tryEnsure(): Boolean {
            if (ws != null) return true

            if (!symSettings.checkLoaded()) return false

            Log.w("msf", "Starting new WebSocket instance")

            ws =
                httpClient.newWebSocket(
                    Request
                        .Builder()
                        .url(
                            "${
                                symSettings.url.replaceFirst(
                                    "http",
                                    "ws",
                                )
                            }${symSettings.satoriPrefix}/v1/events",
                        )
                        .build(),
                    this,
                )

            app.startService(Intent(app, SymAliveService::class.java))

            return true
        }

        fun stop() {
            if (ws == null) return
            ws!!.close(1000, null)
            // Drop current instance
            ws = null
        }

        private fun waitAndRetry() {
            Handler(Looper.getMainLooper()).postDelayed({
                tryEnsure()
            }, 3000)
        }

        override fun onClosed(
            webSocket: WebSocket,
            code: Int,
            reason: String,
        ) {
            if (webSocket != ws) {
                // There's already new instance. Do nothing.
                return
            }

            Log.w("msf", "WebSocket closed ($code). Retrying within 3 seconds.")
            ws = null
            waitAndRetry()
        }

        override fun onClosing(
            webSocket: WebSocket,
            code: Int,
            reason: String,
        ) {
        }

        override fun onFailure(
            webSocket: WebSocket,
            t: Throwable,
            response: Response?,
        ) {
            if (webSocket != ws) {
                // There's already new instance. Do nothing.
                return
            }

            Log.w("msf", "WebSocket failed. Retrying within 3 seconds.", t)
            Log.w("msf", "Response body:\n${response?.body?.string()}")
            ws = null
            waitAndRetry()
        }

        override fun onMessage(
            webSocket: WebSocket,
            text: String,
        ) {
            when (val packet = satoriPacketJsonAdapter.fromJson(text)) {
                is SatoriPacketReady -> {
                    // TODO: put logins into loginsvc
                    Log.w("msf", "Msf READY") // Log logins basic info
                }

                is SatoriPacketPong -> {
                    // Do nothing
                }

                is SatoriPacketEvent -> {
                    // Ignore internal message
                    if (packet.body == null) {
                        Log.w("msf", "Received null body: $text")
                        return
                    }

                    event.onNext(packet.body!!)
                }

                else -> {
                    Log.w("msf", "Received unexpected body: $text")
                }
            }
        }

        override fun onMessage(
            webSocket: WebSocket,
            bytes: ByteString,
        ) {
            Log.w("msf", "Received unexpected raw: $bytes")
        }

        override fun onOpen(
            webSocket: WebSocket,
            response: Response,
        ) {
            Log.w("msf", "WebSocket connection established, sending IDENTIFY")
            webSocket.send(
                satoriPacketIdentifyJsonAdapter.toJson(
                    SatoriPacketIdentify(
                        SatoriIdentify(
                            symSettings.token.ifEmpty { null },
                        ),
                    ),
                ),
            )
        }
    }
