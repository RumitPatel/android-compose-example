package com.rums.android_compose_example.formapp

import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rums.android_compose_example.wellnessapp.Event

class FormViewModel : ViewModel() {
    private val statusMessage = MutableLiveData<Event<String>>()

    fun onResetPasswordClicked(
        currentPassword: String?,
        newPassword: String?,
        confirmNewPassword: String?
    ) {
        if (currentPassword == null || TextUtils.isEmpty(currentPassword)) {
            statusMessage.value = Event("Please enter current password")
        } else if (newPassword == null || TextUtils.isEmpty(newPassword)) {
            statusMessage.value = Event("Please enter current new password")
        } else if (confirmNewPassword == null || TextUtils.isEmpty(confirmNewPassword)) {
            statusMessage.value = Event("Please enter confirm new password")
        } else {
            statusMessage.value = Event("Going to reset the password")
        }

    }

    val message: LiveData<Event<String>>
        get() = statusMessage
}