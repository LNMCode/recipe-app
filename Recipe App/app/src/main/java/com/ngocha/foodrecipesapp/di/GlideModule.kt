package com.ngocha.foodrecipesapp.di

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext

@Module
@InstallIn(ActivityComponent::class)
object GlideModule {

    @Provides
    fun provideRequestManagerGlide(
        @ActivityContext context: Context,
    ) : RequestManager {
        return Glide.with(context)
    }

}
