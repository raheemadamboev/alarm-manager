package xyz.teamgravity.alarmmanager.core.extension

import android.app.AlarmManager
import xyz.teamgravity.alarmmanager.core.util.Helper

fun AlarmManager.hasExactAlarmPermission(): Boolean {
    if (Helper.deviceAtLeastS()) return canScheduleExactAlarms()
    return true
}