package com.example.mywishlistapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mywishlistapp.data.Wish
import kotlinx.coroutines.launch

@Composable
fun AddUpdateWishScreen(
    id: Long,
    viewModel: WishViewModel,
    onAddUpdateButtonClick: () -> Unit,
    onBackButtonClick: () -> Unit
) {
    val snackMessage = remember {
        mutableStateOf("")
    }
    val scope = rememberCoroutineScope()
    val snackBarHostState = remember { SnackbarHostState() }

    Scaffold(
        topBar = {
            AppBar(
                title =
                    if (id == 0L) stringResource(R.string.add_wish)
                    else    stringResource(R.string.update_wish)
            ) {
                onBackButtonClick()
            }
        },
        snackbarHost = {
            SnackbarHost(
                snackBarHostState,
                modifier = Modifier.imePadding()
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .wrapContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(
                modifier = Modifier.padding(10.dp)
            )

            // For wish title
            WishTextField(
                label = "Title",
                value = viewModel.wishTitleState
            )

            Spacer(
                modifier = Modifier.padding(10.dp)
            )

            // For wish description
            WishTextField(
                label = "Description",
                value = viewModel.wishDescriptionState
            )

            Spacer(
                modifier = Modifier.padding(10.dp)
            )

            Button(
                onClick = {
                    if(viewModel.wishTitleState.text != "" && viewModel.wishDescriptionState.text != "") {
                        if(id != 0L) {
                            // update wish
                            viewModel.updateWish(
                                Wish(
                                    id = id,
                                    title = viewModel.wishTitleState.text.toString().trim(),
                                    description = viewModel.wishDescriptionState.text.toString().trim()
                                )
                            )
                        } else {
                            viewModel.addWish(
                                Wish(
                                    title = viewModel.wishTitleState.text.toString().trim(),
                                    description = viewModel.wishDescriptionState.text.toString().trim()
                                )
                            )

                            snackMessage.value = "Wish has been created."
                        }
                        // The following lambda will help navigate to Homescreen
                        onAddUpdateButtonClick()
                    } else {
                        snackMessage.value = "Enter wish title and description to create one."
                        scope.launch {
                            snackBarHostState.showSnackbar(snackMessage.value)
                        }
                    }

                }
            ) {
                Text(
                    text = if(id != 0L) {
                        stringResource(R.string.update_wish)
                    } else {
                        stringResource(R.string.add_wish)
                    },
                    style = TextStyle(
                        fontSize = 18.sp
                    )
                )
            }
        }
    }
}

@Composable
fun WishTextField(
    label: String,
    value: TextFieldState,
) {
    OutlinedTextField(
        state = value,
        label = { Text(label, color = Color.Black) },
        modifier = Modifier.fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text
        ),
        colors = TextFieldDefaults.colors(
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black,
            focusedLabelColor = Color.Black,
            unfocusedLabelColor = Color.Black,
            cursorColor = Color.Black
        )
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewWishTextField() {
    WishTextField(
        label = "Modify wish",
        value = TextFieldState("Get a cat")
    )
}