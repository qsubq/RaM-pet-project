package com.github.qsubq.rampetproject.di

import android.content.Context
import com.github.qsubq.rampetproject.data.InternetConnection
import com.github.qsubq.rampetproject.data.api.ApiService
import com.github.qsubq.rampetproject.data.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class DataModule {

    @ViewModelScoped
    @Provides
    fun provideRepository(api: ApiService): Repository {
        return Repository(api)
    }

    @ViewModelScoped
    @Provides
    fun provideNetworkHelper(@ApplicationContext context: Context):InternetConnection{
        return InternetConnection.ConnectionHelper(context)
    }
}