package com.rums.android_compose_example.formapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun FormScreen(
    modifier: Modifier = Modifier,
    formViewModel: FormViewModel = viewModel()
) {
    var text by rememberSaveable { mutableStateOf("This is my textt") }

    Column(
        modifier = modifier.height(140.dp)
    ) {
        TextField(
            value = text,
            onValueChange = {
                text = it
            },
            label = { Text("Label") }
        )
        Button(
            modifier = Modifier.padding(8.dp),
            onClick = {
                formViewModel.testMethod()
            }
        ) {
            Text(text = "This is my button")
        }


    }
}