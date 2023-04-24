package com.ngocha.foodrecipesapp.di

import com.ngocha.foodrecipesapp.data.repository.accuracy.AccuracyRepository
import com.ngocha.foodrecipesapp.data.repository.accuracy.AccuracyRepositoryImpl
import com.ngocha.foodrecipesapp.data.repository.localMeal.LocalMealRepository
import com.ngocha.foodrecipesapp.data.repository.localMeal.LocalMealRepositoryImpl
import com.ngocha.foodrecipesapp.data.repository.remoteMeal.RemoteMealRepositoryImpl
import com.ngocha.foodrecipesapp.data.repository.remoteMeal.RemoteMeatRepository
import com.ngocha.foodrecipesapp.data.usecases.accuracy.AccuracyUseCase
import com.ngocha.foodrecipesapp.data.usecases.accuracy.AccuracyUseCaseImpl
import com.ngocha.foodrecipesapp.data.usecases.localMeal.LocalMealUseCase
import com.ngocha.foodrecipesapp.data.usecases.localMeal.LocalMealUseCaseImpl
import com.ngocha.foodrecipesapp.data.usecases.remoteMeal.RemoteMealUseCase
import com.ngocha.foodrecipesapp.data.usecases.remoteMeal.RemoteMealUseCaseImpl
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
    abstract fun bindAccuracyRepository(accuracyRepositoryImpl: AccuracyRepositoryImpl): AccuracyRepository

    @Binds
    @Singleton
    abstract fun bindLocalMealUseCase(localMealUseCaseImpl: LocalMealUseCaseImpl): LocalMealUseCase

    @Binds
    @Singleton
    abstract fun bindRemoteMealUseCase(remoteMealUseCaseImpl: RemoteMealUseCaseImpl): RemoteMealUseCase

    @Binds
    @Singleton
    abstract fun bindAccuracyUseCase(accuracyUseCaseImpl: AccuracyUseCaseImpl): AccuracyUseCase
}