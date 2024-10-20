package com.petproject.snackbarfromanywhere.features.feature1.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.petproject.snackbarfromanywhere.core.domain.model.SnackBarAction
import com.petproject.snackbarfromanywhere.core.domain.model.SnackBarEvent
import com.petproject.snackbarfromanywhere.core.presentation.util.SnackbarController
import kotlinx.coroutines.launch

class ScreenOneViewModel: ViewModel() {

    fun showSnackBar(){
        viewModelScope.launch {
            SnackbarController.sendEvent(
                event = SnackBarEvent(
                    snackBarMessage = "Hello Philipp Lackner",
                    snackBarAction = SnackBarAction(
                        name = "Click me",
                        action = {
                            SnackbarController.sendEvent(
                                event = SnackBarEvent(
                                    snackBarMessage = "Thanks for this!"
                                )
                            )
                        }
                    )
                )
            )
        }
    }
}