package com.ngocha.foodrecipesapp.data.repository

import com.ngocha.foodrecipesapp.data.localdb.MealDao
import com.ngocha.foodrecipesapp.data.pojo.Meal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LocalMealRepositoryImpl @Inject constructor(
    private val mealDao: MealDao,
) : LocalMealRepository {

    override suspend fun insertAndUpdateMealIntoDB(meal: Meal) = flow {
        emit(mealDao.insertAndUpdateMealIntoDB(meal))
    }

    override suspend fun deleteMealFromDB(meal: Meal) = flow {
        emit(mealDao.deleteMealFromDB(meal))
    }

    override suspend fun getAllMealsFromDB() = flow { emit(mealDao.getAllMealsFromDB()) }
}