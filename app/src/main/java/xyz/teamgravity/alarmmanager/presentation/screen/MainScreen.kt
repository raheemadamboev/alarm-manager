package xyz.teamgravity.alarmmanager.presentation.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import xyz.teamgravity.alarmmanager.R
import xyz.teamgravity.alarmmanager.presentation.viewmodel.MainViewModel

@Composable
fun MainScreen(
    viewmodel: MainViewModel = hiltViewModel(),
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize(),
    ) {
        TextField(
            value = viewmodel.message,
            onValueChange = viewmodel::onMessageChange,
            label = {
                Text(text = stringResource(id = R.string.message))
            },
            modifier = Modifier.fillMaxWidth(0.8F)
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = viewmodel.time,
            onValueChange = viewmodel::onTimeChange,
            label = {
                Text(text = stringResource(id = R.string.seconds_in_future))
            },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(0.8F)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = viewmodel::onSchedule) {
            Text(text = stringResource(id = R.string.schedule))
        }
    }
}