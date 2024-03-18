package com.petitoff.basiccomposenavigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.petitoff.basiccomposenavigation.ui.theme.BasicComposeNavigationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasicComposeNavigationTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = Route.screen1) {
                    composable(route = Route.screen1) {
                        Screen1(
                            navigateToScreen2 = {
                                navController.navigate(Route.screen2)
                            }
                        )
                    }
                    composable(route = Route.screen2) {
                        Screen2(
                            navigateToScreen3 = {
                                navController.navigate(Route.screen3)
                            },
                            navigateBack = {
                                navController.popBackStack()
                            }
                        )
                    }
                    composable(route = Route.screen3) {
                        Screen3(
                            navigateBack = {
                                navController.popBackStack()
                            },
                            navigateBackToScreen1 = {
                                navController.popBackStack(
                                    route = Route.screen1,
                                    inclusive = false
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}

object Route {
    const val screen1 = "screen1"
    const val screen2 = "screen2"
    const val screen3 = "screen3"
}

@Composable
fun Screen1(
    navigateToScreen2: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Screen 1")
        Button(onClick = navigateToScreen2) {
            Text(text = "Navigate to next screen")
        }
    }
}

@Composable
fun Screen2(
    navigateToScreen3: () -> Unit,
    navigateBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Screen 2")
        Button(onClick = navigateToScreen3) {
            Text(text = "Navigate to next screen")
        }
        Button(onClick = navigateBack) {
            Text(text = "Navigate back")
        }
    }
}

@Composable
fun Screen3(
    navigateBack: () -> Unit,
    navigateBackToScreen1: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Screen 3")
        Button(onClick = navigateBack) {
            Text(text = "Navigate back")
        }
        Button(onClick = navigateBackToScreen1) {
            Text(text = "Navigate back to screen 1")
        }
    }
}