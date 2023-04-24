package com.ngocha.foodrecipesapp.di

import android.content.Context
import androidx.room.Room
import com.ngocha.foodrecipesapp.constants.Constants
import com.ngocha.foodrecipesapp.data.localdb.MealDao
import com.ngocha.foodrecipesapp.data.localdb.MealDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideMealDatabase(
        @ApplicationContext context: Context
    ): MealDatabase =
        Room.databaseBuilder(context, MealDatabase::class.java, Constants.MEAL_DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideMealDao(db: MealDatabase): MealDao = db.mealDao()

}