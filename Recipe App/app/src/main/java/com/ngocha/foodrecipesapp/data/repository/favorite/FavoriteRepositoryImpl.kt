package com.ngocha.foodrecipesapp.data.repository.favorite

import com.google.firebase.firestore.FirebaseFirestore
import com.ngocha.foodrecipesapp.constants.Constants
import com.ngocha.foodrecipesapp.ui.fragments.signIn.SignInState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import java.util.Arrays
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(
    private val fireStore: FirebaseFirestore,
) : FavoriteRepository {

    override suspend fun saveFavoriteMealToFireStore(idMeal: String) = flow {
        try {
            fireStore.collection(Constants.MEAL_DATABASE_NAME).add(
                hashMapOf(
                    "id" to idMeal
                )
            ).await()
            emit(true)
        } catch (e: Exception) {
            emit(false)
        }
    }

    override suspend fun deleteFavorite(idMeal: String) = flow {
        try {
            fireStore.collection(Constants.MEAL_DATABASE_NAME).document(idMeal).delete().await()
            emit(true)
        } catch (e: Exception) {
            emit(false)
        }
    }

    override suspend fun getAllFavoriteFromFireStore() = flow {
        val result = arrayListOf<String>()
        try {
            fireStore.collection(Constants.MEAL_DATABASE_NAME).get().addOnSuccessListener {
                for (doc in it.documents) {
                    val data = doc.data as HashMap<String, String>
                    result.add(data["id"] as String)
                }
            }.await()
            emit(result)
        } catch (e: Exception) {
            emit(arrayListOf())
        }
    }


}