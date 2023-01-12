package com.rums.android_compose_example.formapp

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
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
    private val formViewModel: FormViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this

        initObserver()

        setContent {
            AndroidcomposeexampleTheme {
                Surface(
                    modifier = Modifier.fillMaxWidth(), color = MaterialTheme.colors.background
                ) {
                    FormScreen(onResetButtonClicked = { currentPassword, newPassword, confirmNewPassword ->
                        if (currentPassword == null || TextUtils.isEmpty(currentPassword)) {
                            toast("Please enter current password")
                        } else if (newPassword == null || TextUtils.isEmpty(newPassword)) {
                            toast("Please enter current new password")
                        } else if (confirmNewPassword == null || TextUtils.isEmpty(
                                confirmNewPassword
                            )
                        ) {
                            toast("Please enter confirm new password")
                        } else {
                            toast("Going to reset the password")
                        }
                    })
                }
            }
        }
    }

    private fun initObserver() {
        formViewModel.message.observe(this) { it ->
            it.getContentIfNotHandled()?.let {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
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