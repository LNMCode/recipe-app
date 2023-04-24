package com.ngocha.foodrecipesapp.data.remote

import com.ngocha.foodrecipesapp.data.pojo.CategoryList
import com.ngocha.foodrecipesapp.data.pojo.MealByCategoryList
import com.ngocha.foodrecipesapp.data.pojo.MealsList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApi {

    @GET("random.php")
    suspend fun getRandomMeal(): Response<MealsList>

    @GET("lookup.php")
    suspend fun getMealDetailsById(@Query("i") id: String): Response<MealsList>

    @GET("search.php")
    suspend fun getMealByName(@Query("s") name: String): Response<MealsList>

    @GET("filter.php")
    suspend fun getPopularMeals(@Query("c") categoryName: String): Response<MealByCategoryList>

    @GET("categories.php")
    suspend fun getAllCategories(): Response<CategoryList>

    @GET("filter.php")
    suspend fun getMealsByCategory(@Query("c") nameOfCategory: String): Response<MealByCategoryList>

}