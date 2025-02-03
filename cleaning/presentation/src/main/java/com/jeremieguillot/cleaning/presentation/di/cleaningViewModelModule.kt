package com.jeremieguillot.cleaning.presentation.di

import com.jeremieguillot.cleaning.presentation.declare.DeclareCleaningViewModel
import com.jeremieguillot.cleaning.presentation.submit.SubmitCleaningProofViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module


val cleaningViewModelModule = module {
    viewModelOf(::DeclareCleaningViewModel)
    viewModelOf(::SubmitCleaningProofViewModel)
}