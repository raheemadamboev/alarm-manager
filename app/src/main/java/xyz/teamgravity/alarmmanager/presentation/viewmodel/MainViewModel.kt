package xyz.teamgravity.alarmmanager.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import xyz.teamgravity.alarmmanager.core.util.ReminderAlarm
import xyz.teamgravity.alarmmanager.data.model.ReminderModel
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val handle: SavedStateHandle,
    private val alarm: ReminderAlarm,
) : ViewModel() {

    companion object {
        private const val KEY_MESSAGE = "MainViewModel_KeyMessage"
        private const val DEFAULT_MESSAGE = ""

        private const val KEY_TIME = "MainViewModel_KeyTime"
        private const val DEFAULT_TIME = ""
    }

    var message: String by mutableStateOf(handle[KEY_MESSAGE] ?: DEFAULT_MESSAGE)
        private set

    var time: String by mutableStateOf(handle[KEY_TIME] ?: DEFAULT_TIME)
        private set

    ///////////////////////////////////////////////////////////////////////////
    // API
    ///////////////////////////////////////////////////////////////////////////

    fun onMessageChange(value: String) {
        handle[KEY_MESSAGE] = value
        message = value
    }

    fun onTimeChange(value: String) {
        handle[KEY_TIME] = value
        time = value
    }

    fun onSchedule() {
        alarm.schedule(
            ReminderModel(
                time = LocalDateTime.now()
                    .plusSeconds(time.toLong()),
                message = message,
            )
        )
        onMessageChange("")
        onTimeChange("")
    }
}