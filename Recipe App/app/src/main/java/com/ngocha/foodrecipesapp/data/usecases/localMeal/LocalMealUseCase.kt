package com.ngocha.foodrecipesapp.data.usecases.localMeal

import com.ngocha.foodrecipesapp.data.pojo.Meal
import kotlinx.coroutines.flow.Flow

interface LocalMealUseCase {

    suspend fun insertAndUpdateMealIntoDB(meal: Meal): Flow<Unit>

    suspend fun deleteMealFromDB(meal: Meal): Flow<Unit>

    suspend fun getAllMealsFromDB(): Flow<List<Meal>>

}