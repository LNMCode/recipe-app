package com.ngocha.foodrecipesapp.data.usecases.favorite

import kotlinx.coroutines.flow.Flow

interface FavoriteUseCase {

    suspend fun saveFavoriteMealToFireStore(idMeal: String) : Flow<Boolean>

    suspend fun deleteFavorite(idMeal: String): Flow<Boolean>

    suspend fun getAllFavoriteFromFireStore() : Flow<List<String>>

}