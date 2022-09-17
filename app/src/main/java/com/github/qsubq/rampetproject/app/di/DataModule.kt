package com.github.qsubq.rampetproject.app.di

import android.content.Context
import com.github.qsubq.rampetproject.data.InternetConnection
import com.github.qsubq.rampetproject.data.api.ApiService
import com.github.qsubq.rampetproject.data.repository.RemoteRepositoryImpl
import com.github.qsubq.rampetproject.domain.repository.RemoteRepository
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
    fun provideRepository(api: ApiService): RemoteRepository {
        return RemoteRepositoryImpl(api)
    }

    @ViewModelScoped
    @Provides
    fun provideNetworkHelper(@ApplicationContext context: Context):InternetConnection{
        return InternetConnection.ConnectionHelper(context)
    }
}