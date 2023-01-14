package xyz.teamgravity.alarmmanager.data.model

import java.time.LocalDateTime

data class ReminderModel(
    val time: LocalDateTime,
    val message: String,
)