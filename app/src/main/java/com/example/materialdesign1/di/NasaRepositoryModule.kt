package com.example.materialdesign1.di

import com.example.materialdesign1.data.NasaRepository
import com.example.materialdesign1.domain.model.INasaRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class NasaRepositoryModule {
    @Binds
    abstract fun bindNasaRepository(nasaRepository: NasaRepository): INasaRepository
}