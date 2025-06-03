package com.atm.skymessageapp.di

import com.atm.skymessageapp.data.repository.SocketRepositoryImpl
import com.atm.skymessageapp.domain.repository.SocketRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SocketModule {

    @Singleton
    @Provides
    fun provideSocketService(): SocketRepository = SocketRepositoryImpl("https://yourserver.com")
}