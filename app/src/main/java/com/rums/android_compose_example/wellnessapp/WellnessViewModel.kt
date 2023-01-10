package com.rums.android_compose_example.wellnessapp

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WellnessViewModel : ViewModel() {
    private val _tasks = getWellnessTasks().toMutableStateList()
    val tasks: List<WellnessTask>
        get() = _tasks

    fun remove(item: WellnessTask) {
        _tasks.remove(item)
    }

    private val statusMessage = MutableLiveData<Event<String>>()

    fun onLongClick(item: WellnessTask) {
        statusMessage.value = Event("Clicked on item ${item.label}")
    }

    val message: LiveData<Event<String>>
        get() = statusMessage
}

private fun getWellnessTasks() = List(30) { i -> WellnessTask(i, "Task # $i") }