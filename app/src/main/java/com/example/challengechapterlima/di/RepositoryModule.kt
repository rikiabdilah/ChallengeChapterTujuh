package com.example.challengechapterlima.di

import com.example.challengechapterlima.data.repository.LocalRepository
import com.example.challengechapterlima.data.repository.LocalRepositoryImpl
import com.example.challengechapterlima.data.repository.NetworkRepository
import com.example.challengechapterlima.data.repository.NetworkRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideNetworkRepository(networkRepositoryImpl: NetworkRepositoryImpl): NetworkRepository

    @Binds
    abstract fun provideLocalRepository(localRepositoryImpl: LocalRepositoryImpl): LocalRepository

}