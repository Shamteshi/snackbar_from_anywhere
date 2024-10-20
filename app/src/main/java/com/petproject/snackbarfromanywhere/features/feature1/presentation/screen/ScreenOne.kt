package com.petproject.snackbarfromanywhere.features.feature1.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.petproject.snackbarfromanywhere.core.domain.model.SnackBarEvent
import com.petproject.snackbarfromanywhere.core.presentation.destinations.ScreenOne
import com.petproject.snackbarfromanywhere.core.presentation.util.SnackbarController
import com.petproject.snackbarfromanywhere.features.feature1.presentation.viewmodel.ScreenOneViewModel
import kotlinx.coroutines.launch

@Composable
fun ScreenOne(
    modifier: Modifier = Modifier,
    onNavigate: () -> Unit,
    viewModel: ScreenOneViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val scope = rememberCoroutineScope()
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = {
            scope.launch {
                SnackbarController.sendEvent(
                    event = SnackBarEvent(snackBarMessage = "First screen message")
                )
            }
        }) {
            Text(text = "Show screen one message")
        }
        Button(onClick = {
            scope.launch {
                viewModel.showSnackBar()
            }
        }) {
            Text(text = "Show screen one message")
        }
        Button(onClick = onNavigate) {
            Text(text = "Navigate to screen two")
        }
    }
}