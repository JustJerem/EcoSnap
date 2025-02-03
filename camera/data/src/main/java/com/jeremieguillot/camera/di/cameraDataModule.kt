package com.jeremieguillot.camera.di

import com.jeremieguillot.camera.data.CameraRepositoryImpl
import com.jeremieguillot.camera.domain.CameraRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val cameraDataModule = module {
    singleOf(::CameraRepositoryImpl).bind<CameraRepository>()
}