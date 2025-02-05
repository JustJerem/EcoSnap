package com.jeremieguillot.ecosnap

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.jeremieguillot.camera.presentation.CameraScreenRoot
import com.jeremieguillot.camera.presentation.CameraViewModel
import com.jeremieguillot.cleaning.presentation.declare.CleaningScreenRoot
import com.jeremieguillot.cleaning.presentation.declare.DeclareCleaningContract
import com.jeremieguillot.cleaning.presentation.declare.DeclareCleaningViewModel
import com.jeremieguillot.cleaning.presentation.submit.SubmitCleaningProofContract
import com.jeremieguillot.cleaning.presentation.submit.SubmitCleaningProofScreenRoot
import com.jeremieguillot.cleaning.presentation.submit.SubmitCleaningProofViewModel
import com.jeremieguillot.core.domain.Constants
import com.jeremieguillot.designsystem.EcoSnapTheme
import com.jeremieguillot.home.presentation.HomeScreenRoot
import com.jeremieguillot.home.presentation.HomeViewModel
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EcoSnapTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController, startDestination = Route.Home
                ) {
                    composable<Route.Home> {
                        val viewModel = koinViewModel<HomeViewModel>()
                        HomeScreenRoot(
                            viewModel = viewModel,
                            navigateToCleaningArea = {
                                navController.navigate(
                                    Route.Cleaning
                                )
                            },
                            navigateToCleaningAreaSubmit = { cleaningAreaId ->
                                navController.navigate(Route.Submit(cleaningAreaId))
                            }
                        )
                    }
                    composable<Route.Cleaning> { entry ->
                        val path =
                            entry.savedStateHandle.get<String?>(Constants.ARGUMENT_PHOTO_PATH)
                        val viewModel = koinViewModel<DeclareCleaningViewModel>()
                        viewModel.onAction(
                            DeclareCleaningContract.Action.AddImage(
                                path
                            )
                        )

                        CleaningScreenRoot(viewModel = viewModel,
                            navigateToTakePhoto = { navController.navigate(Route.PictureScreen) },
                            navigateBack = { navController.navigateUp() }
                        )
                    }
                    composable<Route.Submit> { entry ->
                        val viewModel = koinViewModel<SubmitCleaningProofViewModel>()

                        val arguments = entry.toRoute<Route.Submit>()
                        viewModel.onAction(SubmitCleaningProofContract.Action.Load(arguments.id))

                        val path =
                            entry.savedStateHandle.get<String?>(Constants.ARGUMENT_PHOTO_PATH)
                        path?.let {
                            viewModel.onAction(
                                SubmitCleaningProofContract.Action.AddImage(
                                    it
                                )
                            )
                        }
                        SubmitCleaningProofScreenRoot(
                            viewModel = viewModel,
                            navigateToTakePhoto = { navController.navigate(Route.PictureScreen) },
                            onBack = { navController.navigateUp() },
                        )
                    }
                    composable<Route.PictureScreen> {
                        val viewModel = koinViewModel<CameraViewModel>()
                        CameraScreenRoot(
                            viewModel = viewModel,
                            returnPhotoPath = { path ->
                                navController.previousBackStackEntry?.savedStateHandle?.set(
                                    Constants.ARGUMENT_PHOTO_PATH,
                                    path
                                )
                                navController.popBackStack()
                            })
                    }
                }
            }
        }
    }
}


