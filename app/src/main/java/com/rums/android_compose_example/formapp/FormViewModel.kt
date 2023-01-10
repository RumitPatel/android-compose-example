package com.rums.android_compose_example.formapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rums.android_compose_example.wellnessapp.Event
import com.rums.android_compose_example.wellnessapp.WellnessTask

class FormViewModel() : ViewModel() {
    private val statusMessage = MutableLiveData<Event<String>>()

    fun onResetPasswordClicked() {
        statusMessage.value = Event("Clicked on Rest password button")
    }

    val message: LiveData<Event<String>>
        get() = statusMessage
}