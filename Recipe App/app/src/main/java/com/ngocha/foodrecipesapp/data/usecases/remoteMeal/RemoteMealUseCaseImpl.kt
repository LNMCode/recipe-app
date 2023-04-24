package com.ngocha.foodrecipesapp.data.usecases.remoteMeal

import com.ngocha.foodrecipesapp.base.BaseUseCase
import com.ngocha.foodrecipesapp.data.pojo.MealsList
import com.ngocha.foodrecipesapp.data.repository.remoteMeal.RemoteMeatRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RemoteMealUseCaseImpl @Inject constructor(
    private val remoteMealRepository: RemoteMeatRepository,
) : BaseUseCase(), RemoteMealUseCase {

    override suspend fun getRandomMeal() = handleResponseFlow { remoteMealRepository.getRandomMeal() }

    override suspend fun getMealDetailsById(id: String) = handleResponseFlow {
        remoteMealRepository.getMealDetailsById(id)
    }

    override suspend fun getPopularMeals(categoryName: String) = handleResponseFlow {
        remoteMealRepository.getPopularMeals(categoryName)
    }

    override suspend fun getMealByName(name: String)= handleResponseFlow {
        remoteMealRepository.getMealByName(name)
    }

    override suspend fun getAllCategories() = handleResponseFlow {
        remoteMealRepository.getAllCategories()
    }

    override suspend fun getMealsByCategory(nameOfCategory: String) = handleResponseFlow {
        remoteMealRepository.getMealsByCategory(nameOfCategory)
    }

}

