package com.varsha.testproject.di

val apiModule = module {
    fun provideUseApi(retrofit: Retrofit): ImageService{
        return retrofit.create(ImageService::class.java)
    }
    single { provideUseApi(get()) }
}