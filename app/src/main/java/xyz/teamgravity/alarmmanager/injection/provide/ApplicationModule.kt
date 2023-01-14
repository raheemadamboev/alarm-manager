package xyz.teamgravity.alarmmanager.injection.provide

import android.app.AlarmManager
import android.app.Application
import android.app.NotificationManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import xyz.teamgravity.alarmmanager.core.notification.ReminderNotification
import xyz.teamgravity.alarmmanager.core.util.ReminderAlarm
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Provides
    @Singleton
    fun provideNotificationManager(application: Application): NotificationManager =
        application.getSystemService(NotificationManager::class.java)

    @Provides
    @Singleton
    fun provideReminderNotification(
        application: Application,
        notificationManager: NotificationManager,
    ): ReminderNotification = ReminderNotification(
        context = application,
        manager = notificationManager
    )

    @Provides
    @Singleton
    fun provideAlarmManager(application: Application): AlarmManager = application.getSystemService(AlarmManager::class.java)

    @Provides
    @Singleton
    fun provideReminderAlarm(
        application: Application,
        alarmManager: AlarmManager,
    ): ReminderAlarm = ReminderAlarm(
        context = application,
        manager = alarmManager
    )
}