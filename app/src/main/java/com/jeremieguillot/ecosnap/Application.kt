package com.jeremieguillot.ecosnap

import android.app.Application
import com.jeremieguillot.camera.di.cameraDataModule
import com.jeremieguillot.camera.di.cameraViewModelModule
import com.jeremieguillot.cleaning.di.cleaningDataModule
import com.jeremieguillot.cleaning.di.databaseModule
import com.jeremieguillot.cleaning.presentation.di.cleaningViewModelModule
import com.jeremieguillot.di.homeViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class Application : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@Application)
            modules(
                databaseModule,
                cleaningDataModule,
                cleaningViewModelModule,
                homeViewModelModule,
                cameraDataModule,
                cameraViewModelModule,
            )
        }
    }
}
