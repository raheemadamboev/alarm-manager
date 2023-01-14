package xyz.teamgravity.alarmmanager.injection.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import xyz.teamgravity.alarmmanager.core.notification.ReminderNotification
import javax.inject.Inject

@HiltAndroidApp
class App : Application() {

    @Inject
    lateinit var notification: ReminderNotification

    override fun onCreate() {
        super.onCreate()

        notification.createChannel()
    }
}