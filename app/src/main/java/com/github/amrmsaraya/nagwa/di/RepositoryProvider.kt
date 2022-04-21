package com.github.amrmsaraya.nagwa.di

import com.github.amrmsaraya.nagwa.data.repoImpl.FileRepoImpl
import com.github.amrmsaraya.nagwa.data.source.LocalDataSource
import com.github.amrmsaraya.nagwa.domain.repository.FileRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryProvider {

    @Singleton
    @Provides
    fun provideFileRepo(
        localDataSource: LocalDataSource,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): FileRepo {
        return FileRepoImpl(
            localDataSource = localDataSource,
            dispatcher = dispatcher
        )
    }
}