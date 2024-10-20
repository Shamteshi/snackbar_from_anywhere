package com.petproject.snackbarfromanywhere.features.feature2.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.petproject.snackbarfromanywhere.core.presentation.destinations.ScreenTwo

@Composable
fun ScreenTwo(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Text(text = "Screen Two")
    }
}