package com.rums.android_compose_example.wellnessapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
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
import com.rums.android_compose_example.formapp.ResetPasswordActivity
import com.rums.android_compose_example.ui.theme.AndroidcomposeexampleTheme

class WellnessAppActivity : ComponentActivity() {

    private lateinit var mContext: Context
    private val viewModel: WellnessViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this

        initObserver()

        setContent {
            AndroidcomposeexampleTheme {
                Surface(
                    modifier = Modifier.fillMaxWidth(), color = MaterialTheme.colors.background
                ) {
                    WellnessScreen(
                        onNavigateButtonClicked = {
                            navigateToFormActivity()
                        }
                    )
                }
            }
        }
    }

    private fun initObserver() {
        viewModel.message.observe(this) { it ->
            it.getContentIfNotHandled()?.let {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun navigateToFormActivity() {
        startActivity(
            Intent(mContext, ResetPasswordActivity::class.java)
                .putExtra("", "")
        )

    }
}

@Preview
@Composable
fun DefaultPreview() {
    AndroidcomposeexampleTheme {
        WellnessScreen()
    }
}