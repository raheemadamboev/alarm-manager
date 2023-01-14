package xyz.teamgravity.alarmmanager.core.util

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import xyz.teamgravity.alarmmanager.core.receiver.ReminderReceiver
import xyz.teamgravity.alarmmanager.data.model.ReminderModel
import java.time.ZoneId

class ReminderAlarmManager(
    private val context: Context,
    private val manager: AlarmManager,
) {

    ///////////////////////////////////////////////////////////////////////////
    // API
    ///////////////////////////////////////////////////////////////////////////

    fun schedule(reminder: ReminderModel) {
        if (Helper.hasExactAlarmPermission(manager)) {
            val intent = Intent(context, ReminderReceiver::class.java).apply {
                action = ReminderReceiver.ACTION_REMINDER_NOTIFICATION
                putExtra(ReminderReceiver.KEY_MESSAGE, reminder.message)
            }
            val pendingIntent = PendingIntent.getBroadcast(
                context,
                reminder.hashCode(),
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
            manager.setExactAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP,
                reminder.time.atZone(ZoneId.systemDefault()).toEpochSecond() * 1000,
                pendingIntent
            )
        }
    }

    fun cancel(reminder: ReminderModel) {
        manager.cancel(
            PendingIntent.getBroadcast(
                context,
                reminder.hashCode(),
                Intent(context, ReminderReceiver::class.java),
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
        )
    }
}