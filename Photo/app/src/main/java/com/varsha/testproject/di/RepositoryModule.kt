package com.varsha.testproject.di

import com.varsha.testproject.repository.ImageRepository
import org.koin.dsl.module

val repositoryModule = module {
    single {
        ImageRepository(get())
    }
}