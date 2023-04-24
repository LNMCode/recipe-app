package com.ngocha.foodrecipesapp.data.usecases.localMeal

import com.ngocha.foodrecipesapp.base.BaseUseCase
import com.ngocha.foodrecipesapp.data.pojo.Meal
import com.ngocha.foodrecipesapp.data.repository.localMeal.LocalMealRepository
import javax.inject.Inject

class LocalMealUseCaseImpl @Inject constructor(
    private val localMealRepository: LocalMealRepository,
) : BaseUseCase(), LocalMealUseCase {

    override suspend fun insertAndUpdateMealIntoDB(meal: Meal) = handleFlow {
        localMealRepository.insertAndUpdateMealIntoDB(meal)
    }

    override suspend fun deleteMealFromDB(meal: Meal) = handleFlow {
        localMealRepository.deleteMealFromDB(meal)
    }

    override suspend fun getAllMealsFromDB() = handleFlow {
        localMealRepository.getAllMealsFromDB()
    }
}