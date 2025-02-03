package com.jeremieguillot.camera.di

import com.jeremieguillot.camera.presentation.CameraViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val cameraViewModelModule = module {
    viewModelOf(::CameraViewModel)
}