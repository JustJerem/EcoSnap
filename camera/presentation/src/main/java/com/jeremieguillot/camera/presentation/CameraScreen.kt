package com.jeremieguillot.camera.presentation

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Matrix
import android.net.Uri
import android.provider.Settings
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.ImageProxy
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.jeremieguillot.EcosnapButton
import com.jeremieguillot.camera.presentation.composables.CameraOverlay
import kotlinx.coroutines.flow.collectLatest
import java.io.ByteArrayOutputStream

@Composable
fun CameraScreenRoot(
    viewModel: CameraViewModel,
    returnPhotoPath: (String) -> Unit
) {
    HandleCameraPermission()
    LaunchedEffect(viewModel.events) {
        viewModel.events.collectLatest { event ->
            when (event) {
                is CameraContract.Event.ReturnPhotoPath -> {
                    returnPhotoPath(event.path)
                }
            }
        }
    }
    CameraScreen(
        onAction = { action ->
            viewModel.onAction(action)
        }
    )
}

@Composable
fun CameraScreen(
    onAction: (CameraContract.Action) -> Unit = {}
) {
    val applicationContext = LocalContext.current.applicationContext
    val fileDir = applicationContext.filesDir.path
    val controller = remember {
        LifecycleCameraController(applicationContext).apply {
            setEnabledUseCases(
                CameraController.IMAGE_CAPTURE or
                        CameraController.VIDEO_CAPTURE
            )
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        CameraPreview(
            controller = controller,
            modifier = Modifier
                .fillMaxSize()
        )
        CameraOverlay()
        EcosnapButton(
            modifier = Modifier
                .safeContentPadding()
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp),
            text = stringResource(R.string.take_photo),
            onClick = {
                takePhoto(
                    applicationContext = applicationContext,
                    controller = controller,
                    onPhotoTaken = { bitmap ->
                        //We transform the bitmap to ByteArray because the domain does not know Android Bitmap
                        val stream = ByteArrayOutputStream()
                        stream.use {
                            bitmap.compress(
                                Bitmap.CompressFormat.JPEG,
                                50,
                                it
                            )
                        }
                        onAction(CameraContract.Action.TakePhoto(stream.toByteArray(), fileDir))
                    }
                )
            }
        )
    }
}

private fun takePhoto(
    applicationContext: Context,
    controller: LifecycleCameraController,
    onPhotoTaken: (Bitmap) -> Unit
) {
    controller.takePicture(
        ContextCompat.getMainExecutor(applicationContext),
        object : ImageCapture.OnImageCapturedCallback() {
            override fun onCaptureSuccess(image: ImageProxy) {
                super.onCaptureSuccess(image)

                val matrix = Matrix().apply {
                    postRotate(image.imageInfo.rotationDegrees.toFloat())
                }
                val rotatedBitmap = Bitmap.createBitmap(
                    image.toBitmap(),
                    0,
                    0,
                    image.width,
                    image.height,
                    matrix,
                    true
                )

                onPhotoTaken(rotatedBitmap)
            }

            override fun onError(exception: ImageCaptureException) {
                super.onError(exception)
                Log.e("Camera", "Couldn't take photo: ", exception)
            }
        }
    )
}

@Composable
private fun HandleCameraPermission() {
    val context = LocalContext.current
    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        if (!permissions.all { it.value }) {
            // Open app settings if permissions are denied
            context.startActivity(
                Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                    data = Uri.fromParts("package", context.packageName, null)
                }
            )
        }
    }

    LaunchedEffect(Unit) {
        if (!CAMERAX_PERMISSIONS.all {
                ContextCompat.checkSelfPermission(
                    context,
                    it
                ) == PackageManager.PERMISSION_GRANTED
            }) {
            permissionLauncher.launch(CAMERAX_PERMISSIONS)
        }
    }
}

private val CAMERAX_PERMISSIONS = arrayOf(
    Manifest.permission.CAMERA,
)

@Preview
@Composable
private fun CameraScreenPreview() {
    CameraScreen {}
}