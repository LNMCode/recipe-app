package com.ngocha.foodrecipesapp.data.repository.localMeal

import com.ngocha.foodrecipesapp.data.pojo.Meal
import kotlinx.coroutines.flow.Flow

interface LocalMealRepository {

    suspend fun insertAndUpdateMealIntoDB(meal: Meal) : Flow<Unit>

    suspend fun deleteMealFromDB(meal: Meal) : Flow<Unit>

    suspend fun getAllMealsFromDB(): Flow<List<Meal>>

}