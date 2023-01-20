package com.rums.android_compose_example.reset_pasword_form

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.MutableLiveData
import com.rums.android_compose_example.R
import com.rums.android_compose_example.ui.theme.AndroidComposeExampleTheme
import com.rums.android_compose_example.utils.toast

class ResetPasswordActivity : ComponentActivity() {

    private lateinit var mContext: Context
    private val showLoaderStatus = MutableLiveData<Boolean>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this

        setContent {
            AndroidComposeExampleTheme {
                Surface(
                    modifier = Modifier.fillMaxWidth(), color = Color.White
                ) {
                    ResetPasswordScreen(
                        onBackArrowPressed = { super.onBackPressed() },
                        onResetButtonClicked = { currentPassword, newPassword, confirmNewPassword ->
                            resetPassword(currentPassword, newPassword, confirmNewPassword)
                        },
                        showLoaderStatus
                    )
                }
            }
        }
    }

    private fun resetPassword(
        currentPassword: String?, newPassword: String?, confirmNewPassword: String?
    ) {
        if (!newPassword.equals(confirmNewPassword, true)) {
            toast(getString(R.string.password_mismatch))
        } else if (currentPassword.equals(newPassword, true)) {
            toast(getString(R.string.current_passsword_change_password_not_same))
        } else {
            showLoaderStatus.postValue(true)
            toast("Going to reset the password with \ncurrentPassword = $currentPassword\nnewPassword = $newPassword\nconfirmNewPassword = $confirmNewPassword")

            Handler(Looper.getMainLooper()).postDelayed(
                {// This method will be executed once the timer is over
                    showLoaderStatus.postValue(false)
                }, 5000 // value in milliseconds
            )
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    AndroidComposeExampleTheme {
        ResetPasswordScreen()
    }
}
