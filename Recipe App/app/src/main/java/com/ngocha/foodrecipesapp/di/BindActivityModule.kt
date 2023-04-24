package com.ngocha.foodrecipesapp.di

import com.ngocha.foodrecipesapp.common.imageloader.ImageLoader
import com.ngocha.foodrecipesapp.common.imageloader.ImageLoaderImpl
import com.ngocha.foodrecipesapp.common.networkManager.NetworkManager
import com.ngocha.foodrecipesapp.common.networkManager.NetworkManagerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
abstract class BindActivityModule {
    @Binds
    @ActivityScoped
    abstract fun bindImageLoader(imageLoaderImpl: ImageLoaderImpl): ImageLoader

    @Binds
    @ActivityScoped
    abstract fun bindNetworkManager(networkManagerImpl: NetworkManagerImpl): NetworkManager
}