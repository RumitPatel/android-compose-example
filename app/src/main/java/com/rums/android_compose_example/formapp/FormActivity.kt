package com.rums.android_compose_example.formapp

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.rums.android_compose_example.ui.theme.AndroidcomposeexampleTheme
import com.rums.android_compose_example.utils.toast

class FormActivity : ComponentActivity() {

    private lateinit var mContext: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this

        setContent {
            AndroidcomposeexampleTheme {
                Surface(
                    modifier = Modifier.fillMaxWidth(), color = MaterialTheme.colors.background
                ) {
                    FormScreen(onResetButtonClicked = { currentPassword, newPassword, confirmNewPassword ->
                        if (currentPassword == null || TextUtils.isEmpty(currentPassword)) {
                            toast("Please enter current password")
                        } else if (newPassword == null || TextUtils.isEmpty(newPassword)) {
                            toast("Please enter new password")
                        } else if (confirmNewPassword == null || TextUtils.isEmpty(
                                confirmNewPassword
                            )
                        ) {
                            toast("Please enter confirm new password")
                        } else if (newPassword != confirmNewPassword) {
                            toast("New password and confirm password not match")
                        } else {
                            toast("Going to reset the password")
                        }
                    })
                }
            }
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    AndroidcomposeexampleTheme {
        FormScreen()
    }
}