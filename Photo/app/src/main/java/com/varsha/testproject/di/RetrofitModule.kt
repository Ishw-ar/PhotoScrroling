package com.varsha.testproject.di

val retrofitModule = module {
    fun provideGson(): Gson {
        return GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create()
    }
    fun provideHttpClient(): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
        return okHttpClientBuilder.build()
    }
    fun provideRetrofit(factory: Gson, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.flickr.com/")
            .addConverterFactory(GsonConverterFactory.create(factory))
            .client(client)
            .build()
    }
    single { provideGson() }
    single { provideHttpClient() }
    single { provideRetrofit(get(), get()) }
}