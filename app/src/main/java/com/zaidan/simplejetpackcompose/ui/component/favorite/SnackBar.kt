package com.zaidan.simplejetpackcompose.ui.component.favorite

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.constraintlayout.compose.ConstraintLayout

@ExperimentalMaterialApi
@Composable
fun OnActionDeleteFromDb(
    snackBarHostState: SnackbarHostState
) {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val snackBar = createRef()
        SnackbarHost(
            modifier = Modifier.constrainAs(snackBar) {
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            hostState = snackBarHostState,
            snackbar = {
                Snackbar(
                    action = {
                        TextButton(
                            onClick = {
                                snackBarHostState.currentSnackbarData?.dismiss()
                            }
                        ) {
                            Text(
                                text = snackBarHostState.currentSnackbarData?.actionLabel?: "Hide",
                                style = TextStyle(color = Color.White)
                            )
                        }
                    }
                ) {
                    Text(text = snackBarHostState.currentSnackbarData?.message?: "Unknown message")
                }
            }
        )
    }
}