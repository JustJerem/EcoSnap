package com.jeremieguillot.cleaning.di

import androidx.room.Room
import com.jeremieguillot.cleaning.data.database.AppDatabase
import com.jeremieguillot.cleaning.data.database.RoomCleaningAreaDataSource
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            AppDatabase::class.java,
            "cleaning_db"
        ).build()
    }

    single { get<AppDatabase>().cleaningAreaDao() }
    singleOf(::RoomCleaningAreaDataSource)
}