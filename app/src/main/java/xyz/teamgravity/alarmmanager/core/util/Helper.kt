package xyz.teamgravity.alarmmanager.core.util

import android.os.Build

object Helper {

    fun deviceAtLeastS(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
    }
}