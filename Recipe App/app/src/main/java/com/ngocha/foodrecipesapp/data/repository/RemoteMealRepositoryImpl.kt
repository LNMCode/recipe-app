package com.ngocha.foodrecipesapp.data.repository

import com.ngocha.foodrecipesapp.data.localdb.MealDao
import com.ngocha.foodrecipesapp.data.remote.MealApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RemoteMealRepositoryImpl @Inject constructor(
    private val api: MealApi
) : RemoteMeatRepository {

    override suspend fun getRandomMeal() = flow { emit(api.getRandomMeal()) }

    override suspend fun getMealDetailsById(id: String) = flow { emit(api.getMealDetailsById(id)) }

    override suspend fun getPopularMeals(categoryName: String) = flow {
        emit(api.getPopularMeals(categoryName))
    }

    override suspend fun getAllCategories() = flow { emit(api.getAllCategories()) }

    override suspend fun getMealsByCategory(nameOfCategory: String) = flow {
        emit(api.getMealsByCategory(nameOfCategory))
    }
}

