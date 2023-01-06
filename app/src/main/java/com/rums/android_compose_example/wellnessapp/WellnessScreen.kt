package com.rums.android_compose_example.wellnessapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun WellnessScreen(
    modifier: Modifier = Modifier,
    wellnessViewModel: WellnessViewModel = viewModel(),
    onNavigateButtonClicked: () -> Unit = {}
) {
    Column(modifier = modifier) {
        StatefulCounter()
        Button(
            modifier = Modifier.padding(8.dp),
            /*onClick = {
                wellnessViewModel.navigateButtonClicked()
            }*/
            onClick = onNavigateButtonClicked
        ) {
            Text(text = "Navigate to test form")
        }
        MyTaskList(
            list = wellnessViewModel.tasks,
            onCloseTask = { task ->
                wellnessViewModel.remove(task)
            },
            onItemLongClick = { task ->
                wellnessViewModel.onLongClick(task)
            }
        )
    }
}