package com.example.serversmanagement.di

import com.example.serversmanagement.data.remote.ServersApi
import com.example.serversmanagement.repository.ServersRepositoryImpl
import com.example.serversmanagement.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideServersRepository(
        api: ServersApi
    ) = ServersRepositoryImpl(api)

    @Singleton
    @Provides
    fun provideServersApi(): ServersApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ServersApi::class.java)
    }
}