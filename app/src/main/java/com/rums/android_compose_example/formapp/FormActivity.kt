package com.rums.android_compose_example.formapp

import android.content.Context
import android.os.Bundle
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

class FormActivity : ComponentActivity() {

    private lateinit var mContext: Context
    private val mainViewModel: FormViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this

        setContent {
            AndroidcomposeexampleTheme {
                Surface(
                    modifier = Modifier.fillMaxWidth(), color = MaterialTheme.colors.background
                ) {
                    FormScreen()
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