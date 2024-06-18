package com.ilharper.sym.app

import android.app.Application
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationChannelGroupCompat
import androidx.core.app.NotificationManagerCompat
import com.google.android.material.color.DynamicColors
import com.ilharper.sym.alive.SymAliveService
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SymApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        // Apply dynamic color
        DynamicColors.applyToActivitiesIfAvailable(this)

        // Create SymAlive notification channel
        val notificationManager = NotificationManagerCompat.from(this)

        notificationManager.createNotificationChannelGroup(
            NotificationChannelGroupCompat
                .Builder(
                    SymAliveService.CHANNEL_GROUP_NORMAL,
                )
                .setName("一般")
                .setDescription("与 Sym 应用有关的通知。")
                .build(),
        )

        notificationManager
            .createNotificationChannel(
                NotificationChannelCompat
                    .Builder(
                        SymAliveService.CHANNEL_ALIVE,
                        NotificationManagerCompat.IMPORTANCE_LOW,
                    )
                    .setName("Sym 后台")
                    .setDescription("Sym 的后台服务。")
                    .setGroup(SymAliveService.CHANNEL_GROUP_NORMAL)
                    .build(),
            )
    }
}
