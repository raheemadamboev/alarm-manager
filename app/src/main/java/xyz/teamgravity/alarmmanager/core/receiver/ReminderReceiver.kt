package xyz.teamgravity.alarmmanager.core.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import dagger.hilt.android.AndroidEntryPoint
import xyz.teamgravity.alarmmanager.core.notification.ReminderNotification
import javax.inject.Inject

@AndroidEntryPoint
class ReminderReceiver : BroadcastReceiver() {

    companion object {
        const val ACTION_REMINDER_NOTIFICATION = "xyz.teamgravity.alarmmanager.ACTION_REMINDER_NOTIFICATION"
        const val KEY_MESSAGE = "ReminderReceiver_KeyMessage"
    }

    @Inject
    lateinit var notification: ReminderNotification

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == ACTION_REMINDER_NOTIFICATION) {
            notification.show(intent.getStringExtra(KEY_MESSAGE) ?: "")
        }
    }
}