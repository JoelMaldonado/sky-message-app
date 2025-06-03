package com.atm.skymessageapp.di

import com.atm.skymessageapp.data.repository.AuthRepositoryImpl
import com.atm.skymessageapp.domain.repository.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindAuthRepository(imp: AuthRepositoryImpl): AuthRepository
}