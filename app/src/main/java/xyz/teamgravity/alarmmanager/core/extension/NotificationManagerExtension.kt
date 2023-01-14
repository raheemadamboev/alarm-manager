package xyz.teamgravity.alarmmanager.core.extension

import android.app.NotificationManager
import xyz.teamgravity.alarmmanager.core.util.Helper

fun NotificationManager.hasNotificationPermission(): Boolean {
    if (Helper.deviceAtLeastTiramisu()) return areNotificationsEnabled()
    return true
}