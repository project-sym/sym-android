package com.ilharper.sym.alive

import android.Manifest
import android.app.Service
import android.content.Intent
import android.content.pm.PackageManager
import android.os.IBinder
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationChannelGroupCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationCompat.BigTextStyle
import androidx.core.app.NotificationManagerCompat
import com.ilharper.sym.R
import com.ilharper.sym.msf.Msf
import com.ilharper.symri.entity.SatoriEventType
import com.ilharper.symri.rxjava.ofType
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.disposables.Disposable
import javax.inject.Inject

@AndroidEntryPoint
class SymAliveService : Service() {
    @Inject
    lateinit var msf: Msf

    override fun onBind(intent: Intent?): IBinder? {
        return null // As doc request
    }

    private var eventSubscribe: Disposable? = null

    private var notificationId = 0

    override fun onStartCommand(
        intent: Intent?,
        flags: Int,
        startId: Int,
    ): Int {
        val notificationManager = NotificationManagerCompat.from(this)

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS,
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            notificationManager.notify(
                NOTIFICATION_ALIVE,
                NotificationCompat
                    .Builder(
                        this,
                        CHANNEL_ALIVE,
                    )
                    .setGroup(CHANNEL_GROUP_NORMAL)
                    .setContentTitle("Sym 正在运行")
                    .setContentText("Sym 正在后台运行。")
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setPriority(NotificationCompat.PRIORITY_LOW)
                    .setCategory(NotificationCompat.CATEGORY_SERVICE)
                    .build(),
            )
        }

        if (!msf.tryEnsure()) return START_STICKY

        eventSubscribe =
            msf
                .event
                .ofType(SatoriEventType.MESSAGE_CREATED)
                .subscribe { event ->
                    val groupId =
                        "com.ilharper.sym.notification.channel.group.platform.${event.platform}"
                    val channelId =
                        "com.ilharper.sym.notification.channel.channel.${event.platform}.${event.channel!!.id}"
                    val channelName = event.platform
                    val title = event.channel!!.name ?: "频道 ${event.channel!!.id}"
                    val text = event.message!!.content?.joinToString("") ?: "（不支持的消息）"

                    notificationManager.createNotificationChannelGroup(
                        NotificationChannelGroupCompat
                            .Builder(
                                groupId,
                            )
                            .setName(channelName)
                            .build(),
                    )

                    notificationManager
                        .createNotificationChannel(
                            NotificationChannelCompat
                                .Builder(
                                    channelId,
                                    NotificationManagerCompat.IMPORTANCE_MAX,
                                )
                                .setName(title)
                                .setGroup(groupId)
                                .build(),
                        )

                    notificationManager.notify(
                        notificationId++,
                        NotificationCompat
                            .Builder(
                                this,
                                channelId,
                            )
                            .setGroup(groupId)
                            .setContentTitle(title)
                            .setContentText(text)
                            .setSmallIcon(R.drawable.ic_launcher_foreground)
                            .setStyle(
                                BigTextStyle()
                                    .bigText(text),
                            )
                            .setPriority(NotificationCompat.PRIORITY_MAX)
                            .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                            .build(),
                    )
                }

        return START_STICKY
    }

    override fun onDestroy() {
        eventSubscribe?.dispose()

        startService(Intent(this, SymAliveService::class.java))
    }

    companion object {
        const val CHANNEL_GROUP_NORMAL = "com.ilharper.sym.notification.channel.group.NORMAL"
        const val CHANNEL_ALIVE = "com.ilharper.sym.notification.channel.ALIVE"
        const val NOTIFICATION_ALIVE = 1
    }
}
