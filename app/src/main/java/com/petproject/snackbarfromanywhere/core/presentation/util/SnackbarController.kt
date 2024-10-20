package com.petproject.snackbarfromanywhere.core.presentation.util

import com.petproject.snackbarfromanywhere.core.domain.model.SnackBarEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

object SnackbarController {
    private val _events = Channel<SnackBarEvent>()
    val events = _events.receiveAsFlow()

    suspend fun sendEvent(event: SnackBarEvent){
        _events.send(event)
    }
}