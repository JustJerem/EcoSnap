package com.jeremieguillot.cleaning.di

import com.jeremieguillot.cleaning.data.CleaningRepositoryImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val cleaningDataModule = module {
    singleOf(::CleaningRepositoryImpl).bind<com.jeremieguillot.core.domain.CleaningRepository>()
}