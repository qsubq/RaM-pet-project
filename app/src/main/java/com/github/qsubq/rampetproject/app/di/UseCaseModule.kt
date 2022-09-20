package com.github.qsubq.rampetproject.app.di

import com.github.qsubq.rampetproject.domain.repository.RemoteRepository
import com.github.qsubq.rampetproject.domain.useCase.GetAllCharacterUseCase
import com.github.qsubq.rampetproject.domain.useCase.GetAllEpisodesUseCase
import com.github.qsubq.rampetproject.domain.useCase.GetRandomCharacterUseCase
import com.github.qsubq.rampetproject.domain.useCase.GetSearchCharacterUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@InstallIn(ViewModelComponent::class)
@Module
class UseCaseModule {

    @ViewModelScoped
    @Provides
    fun provideGetAllEpisodesUseCase(repository: RemoteRepository): GetAllEpisodesUseCase {
        return GetAllEpisodesUseCase(repository)
    }

    @ViewModelScoped
    @Provides
    fun provideGetRandomCharacterUseCase(repository: RemoteRepository): GetRandomCharacterUseCase {
        return GetRandomCharacterUseCase(repository)
    }

    @ViewModelScoped
    @Provides
    fun provideGetSearchCharacterUseCase(repository: RemoteRepository): GetSearchCharacterUseCase {
        return GetSearchCharacterUseCase(repository)
    }

    @ViewModelScoped
    @Provides
    fun provideGetAllCharacterUseCase(repository: RemoteRepository): GetAllCharacterUseCase {
        return GetAllCharacterUseCase(repository)
    }
}