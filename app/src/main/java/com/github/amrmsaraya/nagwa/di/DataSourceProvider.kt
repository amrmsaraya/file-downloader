package com.github.amrmsaraya.nagwa.di

import android.content.Context
import com.github.amrmsaraya.nagwa.data.source.LocalDataSource
import com.github.amrmsaraya.nagwa.data.sourceImpl.LocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceProvider {

    @Singleton
    @Provides
    fun provideLocalDataSource(@ApplicationContext context: Context): LocalDataSource {
        return LocalDataSourceImpl(context)
    }
}