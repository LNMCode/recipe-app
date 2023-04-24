package com.ngocha.foodrecipesapp.data.repository.favorite

import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {

    suspend fun saveFavoriteMealToFireStore(idMeal: String) : Flow<Boolean>

    suspend fun deleteFavorite(idMeal: String): Flow<Boolean>

    suspend fun getAllFavoriteFromFireStore() : Flow<List<String>>

}