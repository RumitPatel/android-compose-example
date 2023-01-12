package com.rums.android_compose_example.formapp

import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rums.android_compose_example.wellnessapp.Event

class FormViewModel : ViewModel() {
    private val statusMessage = MutableLiveData<Event<String>>()

    val message: LiveData<Event<String>>
        get() = statusMessage
}