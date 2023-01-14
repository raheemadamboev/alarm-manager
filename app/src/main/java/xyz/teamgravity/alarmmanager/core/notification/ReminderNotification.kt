package xyz.teamgravity.alarmmanager.core.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import xyz.teamgravity.alarmmanager.R
import xyz.teamgravity.alarmmanager.core.extension.hasNotificationPermission
import xyz.teamgravity.alarmmanager.presentation.activity.Main

class ReminderNotification(
    private val context: Context,
    private val manager: NotificationManager,
) {

    companion object {
        const val CHANNEL_ID = "xyz.teamgravity.alarmmanager_REMINDER_NOTIFICATION"
        const val NOTIFICATION_ID = 123
    }

    ///////////////////////////////////////////////////////////////////////////
    // API
    ///////////////////////////////////////////////////////////////////////////

    fun createChannel() {
        NotificationChannel(
            CHANNEL_ID,
            context.getString(R.string.notification_reminder_name),
            NotificationManager.IMPORTANCE_HIGH
        ).apply {
            description = context.getString(R.string.notification_reminder_description)
            manager.createNotificationChannel(this)
        }
    }

    fun show(message: String) {
        if (manager.hasNotificationPermission()) {
            val intent = Intent(context, Main::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            }
            val flag = PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE

            val builder = NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_alarm)
                .setContentTitle(context.getString(R.string.notification_reminder_name))
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setContentIntent(PendingIntent.getActivity(context, 0, intent, flag))
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_MAX)
            val notification = NotificationCompat.BigTextStyle(builder)
                .bigText(message)
                .build()

            manager.notify(NOTIFICATION_ID, notification)
        }
    }
}