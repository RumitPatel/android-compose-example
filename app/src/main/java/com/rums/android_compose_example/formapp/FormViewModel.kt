package com.rums.android_compose_example.formapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FormViewModel() : ViewModel() {
    private val keyword = MutableLiveData<String>()

    fun testMethod() {
        val keywordValue = keyword.value ?: return
        if (keywordValue.isBlank()) {
            return
        }
    }
}