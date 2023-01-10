package com.rums.android_compose_example.formapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun FormScreen(
    modifier: Modifier = Modifier,
    formViewModel: FormViewModel = viewModel(),
    onResetPasswordClicked: () -> Unit = {}
) {
    var currentPassword by rememberSaveable { mutableStateOf("") }
    var currentPasswordVisible by rememberSaveable { mutableStateOf(false) }

    var newPassword by rememberSaveable { mutableStateOf("") }
    var newPasswordVisible by rememberSaveable { mutableStateOf(false) }

    var confirmNewPassword by rememberSaveable { mutableStateOf("") }
    var confirmNewPasswordVisible by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier.padding(4.dp)
    ) {
        MyTextField(currentPassword,
            placeholder = "Type current password here",
            label = "Enter current password",
            onValueChange = {
                currentPassword = it
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password, imeAction = ImeAction.Next
            ),
            visualTransformation = if (currentPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val image = if (currentPasswordVisible) Icons.Filled.Visibility
                else Icons.Filled.VisibilityOff

                // Please provide localized description for accessibility services
                val description = if (currentPasswordVisible) "Hide password" else "Show password"

                IconButton(onClick = { currentPasswordVisible = !currentPasswordVisible }) {
                    Icon(imageVector = image, description)
                }
            })
        MyTextField(newPassword,
            placeholder = "Type new password here",
            label = "Enter new password",
            onValueChange = {
                newPassword = it
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password, imeAction = ImeAction.Next
            ),
            visualTransformation = if (newPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val image = if (newPasswordVisible) Icons.Filled.Visibility
                else Icons.Filled.VisibilityOff

                // Please provide localized description for accessibility services
                val description = if (newPasswordVisible) "Hide password" else "Show password"

                IconButton(onClick = { newPasswordVisible = !newPasswordVisible }) {
                    Icon(imageVector = image, description)
                }
            })
        MyTextField(confirmNewPassword,
            placeholder = "Type confirm new password here",
            label = "Enter confirm new password",
            onValueChange = {
                confirmNewPassword = it
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password, imeAction = ImeAction.Done
            ),
            visualTransformation = if (confirmNewPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val image = if (confirmNewPasswordVisible) Icons.Filled.Visibility
                else Icons.Filled.VisibilityOff

                // Please provide localized description for accessibility services
                val description =
                    if (confirmNewPasswordVisible) "Hide password" else "Show password"

                IconButton(onClick = { confirmNewPasswordVisible = !confirmNewPasswordVisible }) {
                    Icon(imageVector = image, description)
                }
            })
        Button(modifier = Modifier.padding(8.dp), onClick = {
            formViewModel.onResetPasswordClicked(
                currentPassword,
                newPassword,
                confirmNewPassword
            )
        }) {
            Text(text = "Reset password")
        }
    }
}

@Composable
private fun MyTextField(
    value: String,
    placeholder: String,
    label: String,
    onValueChange: (String) -> Unit,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    trailingIcon: @Composable (() -> Unit)? = null,
) {
    TextField(
        value = value,
        placeholder = { Text(placeholder) },
        label = { Text(label) },
        onValueChange = onValueChange,
        singleLine = true,
        keyboardOptions = keyboardOptions,
        visualTransformation = visualTransformation,
        trailingIcon = trailingIcon,
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
    )
}