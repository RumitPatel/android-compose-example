package com.rums.android_compose_example.wellnessapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun StatefulCounter(modifier: Modifier = Modifier) {
    var count by rememberSaveable { mutableStateOf(0) }
    var juiceCount by rememberSaveable { mutableStateOf(0) }
// ------
    Row(
        modifier = modifier.height(180.dp)
    ) {
        StatelessCounter(
            name = "Water",
            count = count,
            onIncrement = { count++ },
            modifier = modifier.weight(weight = 1f)
        )
        StatelessCounter(
            name = "Juice",
            count = juiceCount,
            onIncrement = { juiceCount++ },
            modifier = modifier.weight(1f)
        )
    }
}

@Composable
private fun StatelessCounter(
    name: String,
    count: Int,
    onIncrement: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(16.dp)) {

        if (count > 0) {
            Text(
                text = "You've had $count glasses of $name",
                modifier = modifier.padding(18.dp)
            )
        }

        Button(
            onClick = onIncrement,
            enabled = count < 5,
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text(text = "Add one")
        }
    }
}