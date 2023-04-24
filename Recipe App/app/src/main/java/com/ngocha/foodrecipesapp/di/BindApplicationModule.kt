package com.ngocha.foodrecipesapp.di

import com.ngocha.foodrecipesapp.common.imageloader.ImageLoader
import com.ngocha.foodrecipesapp.common.imageloader.ImageLoaderImpl
import com.ngocha.foodrecipesapp.common.networkManager.NetworkManager
import com.ngocha.foodrecipesapp.common.networkManager.NetworkManagerImpl
import com.ngocha.foodrecipesapp.data.repository.LocalMealRepository
import com.ngocha.foodrecipesapp.data.repository.LocalMealRepositoryImpl
import com.ngocha.foodrecipesapp.data.repository.RemoteMealRepositoryImpl
import com.ngocha.foodrecipesapp.data.repository.RemoteMeatRepository
import com.ngocha.foodrecipesapp.data.usecases.LocalMealUseCase
import com.ngocha.foodrecipesapp.data.usecases.LocalMealUseCaseImpl
import com.ngocha.foodrecipesapp.data.usecases.RemoteMealUseCase
import com.ngocha.foodrecipesapp.data.usecases.RemoteMealUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class BindApplicationModule {
    @Binds
    @Singleton
    abstract fun bindLocalMealRepository(localMealRepositoryImpl: LocalMealRepositoryImpl): LocalMealRepository

    @Binds
    @Singleton
    abstract fun bindRemoteMealRepository(remoteMealRepositoryImpl: RemoteMealRepositoryImpl): RemoteMeatRepository

    @Binds
    @Singleton
    abstract fun bindLocalMealUseCase(localMealUseCaseImpl: LocalMealUseCaseImpl): LocalMealUseCase

    @Binds
    @Singleton
    abstract fun bindRemoteMealUseCase(remoteMealUseCaseImpl: RemoteMealUseCaseImpl): RemoteMealUseCase
}