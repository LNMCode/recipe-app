package com.ngocha.foodrecipesapp.data.usecases

import com.ngocha.foodrecipesapp.data.pojo.CategoryList
import com.ngocha.foodrecipesapp.data.pojo.MealByCategoryList
import com.ngocha.foodrecipesapp.data.pojo.MealsList
import kotlinx.coroutines.flow.Flow

interface RemoteMealUseCase {

    suspend fun getRandomMeal(): Flow<MealsList?>

    suspend fun getMealDetailsById(id: String): Flow<MealsList>

    suspend fun getPopularMeals(categoryName: String): Flow<MealByCategoryList?>

    suspend fun getAllCategories(): Flow<CategoryList?>

    suspend fun getMealsByCategory(nameOfCategory: String): Flow<MealByCategoryList?>

}