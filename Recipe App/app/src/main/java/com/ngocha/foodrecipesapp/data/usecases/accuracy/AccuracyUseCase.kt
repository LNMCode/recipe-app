package com.ngocha.foodrecipesapp.data.usecases.accuracy

import com.ngocha.foodrecipesapp.ui.fragments.signIn.SignInState
import com.ngocha.foodrecipesapp.ui.fragments.signUp.SignUpState
import kotlinx.coroutines.flow.Flow

interface AccuracyUseCase {
    suspend fun signInWithEmailAndPassword(email: String, password: String) : Flow<SignInState>

    suspend fun signUpWithEmailAndPassword(email: String, password: String) : Flow<SignUpState>

    suspend fun isLogged(): Flow<Boolean>

    suspend fun logout(): Flow<Boolean>
}