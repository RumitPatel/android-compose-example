package com.rums.android_compose_example.formapp

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity

class FormActivity : ComponentActivity() {

    private lateinit var mContext: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
    }
}