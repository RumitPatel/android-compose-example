package com.rums.android_compose_example.formapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun ResetPasswordScreen(
    onBackArrowPressed: () -> Unit = {},
    onResetButtonClicked: (currentPassword: String?, newPassword: String?, confirmNewPassword: String?) -> Unit = { _: String?, _: String?, _: String? -> }
) {
    var currentPassword by rememberSaveable { mutableStateOf("") }
    var currentPasswordVisible by rememberSaveable { mutableStateOf(false) }

    var newPassword by rememberSaveable { mutableStateOf("") }
    var newPasswordVisible by rememberSaveable { mutableStateOf(false) }

    var confirmNewPassword by rememberSaveable { mutableStateOf("") }
    var confirmNewPasswordVisible by rememberSaveable { mutableStateOf(false) }

    Scaffold(topBar = {
        TopAppBar(title = {
            Text(text = "Reset Password")
        }, navigationIcon = {
            IconButton(onClick = onBackArrowPressed) {
                Icon(Icons.Filled.ArrowBack, "")
            }
        }, backgroundColor = Color.Blue, contentColor = Color.White, elevation = 12.dp
        )
    }, content = { padding ->
        Column(
            modifier = Modifier.padding(padding)
        ) {
            MyTextField(currentPassword,
                currentPasswordVisible,
                placeholder = "Type current password here",
                label = "Enter current password",
                onValueChange = {
                    currentPassword = it
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password, imeAction = ImeAction.Next
                ),
                trailingIcon = {
                    val image = getPasswordToggleIcon(currentPasswordVisible)
                    val description = getPasswordToggleDescription(confirmNewPasswordVisible)

                    IconButton(onClick = { currentPasswordVisible = !currentPasswordVisible }) {
                        Icon(imageVector = image, description)
                    }
                })

            MyTextField(newPassword,
                newPasswordVisible,
                placeholder = "Type new password here",
                label = "Enter new password",
                onValueChange = {
                    newPassword = it
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password, imeAction = ImeAction.Next
                ),
                trailingIcon = {
                    val image = getPasswordToggleIcon(newPasswordVisible)
                    val description = getPasswordToggleDescription(confirmNewPasswordVisible)

                    IconButton(onClick = { newPasswordVisible = !newPasswordVisible }) {
                        Icon(imageVector = image, description)
                    }
                })

            MyTextField(confirmNewPassword,
                confirmNewPasswordVisible,
                placeholder = "Type confirm new password here",
                label = "Enter confirm new password",
                onValueChange = {
                    confirmNewPassword = it
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password, imeAction = ImeAction.Done
                ),
                trailingIcon = {
                    val image = getPasswordToggleIcon(confirmNewPasswordVisible)
                    val description = getPasswordToggleDescription(confirmNewPasswordVisible)

                    IconButton(onClick = {
                        confirmNewPasswordVisible = !confirmNewPasswordVisible
                    }) {
                        Icon(imageVector = image, description)
                    }
                })

            Button(modifier = Modifier.padding(8.dp), onClick = {
                onResetButtonClicked(currentPassword, newPassword, confirmNewPassword)
            }) {
                Text(text = "Reset password")
            }
        }
    })


}

fun getPasswordToggleIcon(isVisible: Boolean): ImageVector {
    return if (isVisible) Icons.Filled.Visibility
    else Icons.Filled.VisibilityOff
}

fun getPasswordToggleDescription(isVisible: Boolean): String {
    return if (isVisible) "Hide password" else "Show password"
}

@Composable
private fun MyTextField(
    value: String,
    valueVisibility: Boolean,
    placeholder: String,
    label: String,
    onValueChange: (String) -> Unit,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    trailingIcon: @Composable (() -> Unit)? = null,
) {
    TextField(
        value = value,
        placeholder = { Text(placeholder) },
        label = { Text(label) },
        onValueChange = onValueChange,
        singleLine = true,
        keyboardOptions = keyboardOptions,
        visualTransformation = if (valueVisibility) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = trailingIcon,
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
    )
}