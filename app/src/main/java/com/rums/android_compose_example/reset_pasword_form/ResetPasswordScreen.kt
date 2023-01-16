package com.rums.android_compose_example.reset_pasword_form

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rums.android_compose_example.R
import com.rums.android_compose_example.ui.common_views.MyPasswordTextField
import com.rums.android_compose_example.ui.common_views.MyText

@Composable
fun ResetPasswordScreen(
    onBackArrowPressed: () -> Unit = {},
    onResetButtonClicked: (currentPassword: String?, newPassword: String?, confirmNewPassword: String?) -> Unit = { _: String?, _: String?, _: String? -> }
) {
    var currentPassword by rememberSaveable { mutableStateOf("") }
    var currentPasswordVisible by rememberSaveable { mutableStateOf(false) }
    var isErrorCurrentPassword by rememberSaveable { mutableStateOf(false) }

    var newPassword by rememberSaveable { mutableStateOf("") }
    var newPasswordVisible by rememberSaveable { mutableStateOf(false) }
    var isErrorNewPassword by rememberSaveable { mutableStateOf(false) }

    var confirmNewPassword by rememberSaveable { mutableStateOf("") }
    var confirmNewPasswordVisible by rememberSaveable { mutableStateOf(false) }
    var isErrorConfirmNewPassword by rememberSaveable { mutableStateOf(false) }

    Scaffold(topBar = {
        TopAppBar(title = {
            MyText(text = stringResource(R.string.reset_password))
        }, navigationIcon = {
            IconButton(onClick = onBackArrowPressed) {
                Icon(Icons.Filled.ArrowBack, "")
            }
        }, backgroundColor = Color.White,
//            contentColor = Color.White,
            elevation = 12.dp
        )
    }, content = { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painterResource(id = R.drawable.ic_reset_password),
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
                    .padding(8.dp),
                contentScale = ContentScale.Crop
            )
            MyText(
                modifier = Modifier.padding(2.dp),
                text = stringResource(R.string.reset_password),
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp
            )
            MyText(
                modifier = Modifier.padding(12.dp),
                textAlign = TextAlign.Center,
                text = stringResource(R.string.if_you_change_password_your_current_new_password_here_otherwise_leave),
                fontSize = 18.sp,
            )

            MyPasswordTextField(value = currentPassword,
                isError = isErrorCurrentPassword,
                errorMessage = stringResource(R.string.enter_current_password),
                valueVisibility = currentPasswordVisible,
                placeholder = stringResource(R.string.current_password),
                label = stringResource(R.string.current_password),
                onValueChange = {
                    currentPassword = it
                    isErrorCurrentPassword = false
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

            MyPasswordTextField(value = newPassword,
                isError = isErrorNewPassword,
                errorMessage = stringResource(R.string.enter_new_password),
                valueVisibility = newPasswordVisible,
                placeholder = stringResource(R.string.new_password),
                label = stringResource(R.string.new_password),
                onValueChange = {
                    newPassword = it
                    isErrorNewPassword = false
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

            MyPasswordTextField(value = confirmNewPassword,
                isError = isErrorConfirmNewPassword,
                errorMessage = stringResource(R.string.enter_valid_confirm_password),
                valueVisibility = confirmNewPasswordVisible,
                placeholder = stringResource(R.string.confirm_password),
                label = stringResource(R.string.confirm_password),
                onValueChange = {
                    confirmNewPassword = it
                    isErrorConfirmNewPassword = false
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

            Button(modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .height(52.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = colorResource(R.color.colorPrimary)
                ),
                onClick = {
                    if (currentPassword.isEmpty()) {
                        isErrorCurrentPassword = true
                    } else if (newPassword.isEmpty()) {
                        isErrorNewPassword = true
                    } else if (confirmNewPassword.isEmpty()) {
                        isErrorConfirmNewPassword = true
                    } else {
                        onResetButtonClicked(currentPassword, newPassword, confirmNewPassword)
                    }

                }) {
                MyText(
                    text = stringResource(R.string.reset_password).uppercase(),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
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