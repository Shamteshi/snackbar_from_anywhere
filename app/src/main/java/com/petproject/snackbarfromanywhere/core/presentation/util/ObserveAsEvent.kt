package com.petproject.snackbarfromanywhere.core.presentation.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

@Composable
fun <T> ObserveAsEvent(
    flow: Flow<T>,
    key1: Any? = null,
    onEvent: (T) -> Unit
) {
    val lifeCycle = LocalLifecycleOwner.current //this gets the reference to the local lifecycle
    LaunchedEffect(
        lifeCycle.lifecycle,
        key1,
        flow
    ) { //fire the launched effect with a new lifecycle whenever the lifecycle changes
        lifeCycle.repeatOnLifecycle(Lifecycle.State.STARTED){ //repeatOnLifecycle collects flows in a lifecycle aware manner.
            //passing STARTED state will recall the block whenever the lifecycle is in the started state, e.g after configuration change
            withContext(Dispatchers.Main.immediate){//processes the pending tasks immediately if already in the main thread, or else it switches to the main thread and queues the pending task
                flow.collect(onEvent)

            }
        }
    }
}