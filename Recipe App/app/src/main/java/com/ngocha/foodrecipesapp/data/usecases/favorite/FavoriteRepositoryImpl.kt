package com.ngocha.foodrecipesapp.data.usecases.favorite

import com.google.firebase.firestore.FirebaseFirestore
import com.ngocha.foodrecipesapp.base.BaseUseCase
import com.ngocha.foodrecipesapp.constants.Constants
import com.ngocha.foodrecipesapp.data.repository.favorite.FavoriteRepository
import com.ngocha.foodrecipesapp.ui.fragments.signIn.SignInState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import java.util.Arrays
import javax.inject.Inject

class FavoriteUseCaseImpl @Inject constructor(
    private val favoriteRepository: FavoriteRepository,
) : BaseUseCase(), FavoriteUseCase {

    override suspend fun saveFavoriteMealToFireStore(idMeal: String) = handleFlow {
        favoriteRepository.saveFavoriteMealToFireStore(idMeal)
    }

    override suspend fun deleteFavorite(idMeal: String) = handleFlow {
        favoriteRepository.deleteFavorite(idMeal)
    }

    override suspend fun getAllFavoriteFromFireStore() = handleFlow {
        favoriteRepository.getAllFavoriteFromFireStore()
    }
}