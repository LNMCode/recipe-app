package com.ngocha.foodrecipesapp.data.repository

import com.ngocha.foodrecipesapp.data.pojo.CategoryList
import com.ngocha.foodrecipesapp.data.pojo.MealByCategoryList
import com.ngocha.foodrecipesapp.data.pojo.MealsList
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface RemoteMeatRepository {

    suspend fun getRandomMeal(): Flow<Response<MealsList>>

    suspend fun getMealDetailsById(id: String): Flow<Response<MealsList>>

    suspend fun getPopularMeals(categoryName: String): Flow<Response<MealByCategoryList>>

    suspend fun getAllCategories(): Flow<Response<CategoryList>>

    suspend fun getMealsByCategory(nameOfCategory: String): Flow<Response<MealByCategoryList>>

}