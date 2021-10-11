package com.varsha.testproject.di

val repositoryModule = module {
    single {
        ImageRepository(get())
    }
}