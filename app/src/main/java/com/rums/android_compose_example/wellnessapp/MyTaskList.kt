package com.rums.android_compose_example.wellnessapp

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MyTaskList(
    list: List<WellnessTask>, onCloseTask: (WellnessTask) -> Unit, onItemLongClick: (WellnessTask) -> Unit, modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(items = list, key = { task -> task.id }) { task ->
            MyTaskListItem(taskName = task.label, onClose = { onCloseTask(task) }, onItemLongClick = {onItemLongClick(task)})
        }
    }
}