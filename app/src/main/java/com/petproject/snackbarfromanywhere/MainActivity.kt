package com.petproject.snackbarfromanywhere

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.petproject.snackbarfromanywhere.core.presentation.destinations.ScreenOne
import com.petproject.snackbarfromanywhere.core.presentation.destinations.ScreenTwo
import com.petproject.snackbarfromanywhere.core.presentation.util.ObserveAsEvent
import com.petproject.snackbarfromanywhere.core.presentation.util.SnackbarController
import com.petproject.snackbarfromanywhere.features.feature1.presentation.screen.ScreenOne
import com.petproject.snackbarfromanywhere.features.feature2.presentation.screen.ScreenTwo
import com.petproject.snackbarfromanywhere.ui.theme.SnackBarFromAnywhereTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navigator = rememberNavController()
            val snackBarHostState = remember {
                SnackbarHostState()
            }
            val scope = rememberCoroutineScope()
            //observeAsEvent always runs whenever we send something into the snackbar controller using the sendEvent function
            ObserveAsEvent(flow = SnackbarController.events, snackBarHostState) { events ->
                scope.launch {
                    snackBarHostState.currentSnackbarData?.dismiss()
                    val result = snackBarHostState.showSnackbar(message = events.snackBarMessage, actionLabel = events.snackBarAction?.name,
                        duration = SnackbarDuration.Short)

                    if(result == SnackbarResult.ActionPerformed){
                        events.snackBarAction?.action?.invoke()
                    }
                }
            }
            SnackBarFromAnywhereTheme {
                Scaffold(modifier = Modifier.fillMaxSize(),
                    snackbarHost = { SnackbarHost(hostState = snackBarHostState) }) { innerPadding ->
                    //wrap NavHost with scaffold in order to prevent hiding of the snackbar when the user navigates to another screen
                    NavHost(
                        modifier = Modifier.padding(innerPadding),
                        navController = navigator, startDestination = ScreenOne
                    ) {
                        composable<ScreenOne> {
                            com.petproject.snackbarfromanywhere.features.feature1.presentation.screen.ScreenOne(
                                onNavigate = {navigator.navigate(ScreenTwo)})
                        }
                        composable<ScreenTwo> {
                            com.petproject.snackbarfromanywhere.features.feature2.presentation.screen.ScreenTwo()
                        }
                    }
                }
            }
        }
    }
}
