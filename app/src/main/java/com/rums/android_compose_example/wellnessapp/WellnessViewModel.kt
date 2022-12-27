package com.rums.android_compose_example.wellnessapp

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel

class WellnessViewModel : ViewModel() {
    private val _tasks = getWellnessTasks().toMutableStateList()
    val tasks: List<WellnessTask>
        get() = _tasks

    fun remove(item: WellnessTask) {
        _tasks.remove(item)
    }

    fun onLongClick(item: WellnessTask) {
    }
}

private fun getWellnessTasks() = List(30) { i -> WellnessTask(i, "Task # $i") }