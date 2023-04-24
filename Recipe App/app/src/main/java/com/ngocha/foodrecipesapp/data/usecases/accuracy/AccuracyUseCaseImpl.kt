package com.ngocha.foodrecipesapp.data.usecases.accuracy

import com.ngocha.foodrecipesapp.base.BaseUseCase
import com.ngocha.foodrecipesapp.data.repository.accuracy.AccuracyRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AccuracyUseCaseImpl @Inject constructor(
    private val accuracyRepository: AccuracyRepository,
) : BaseUseCase(), AccuracyUseCase {
    override suspend fun signInWithEmailAndPassword(email: String, password: String) = handleFlow {
        accuracyRepository.signInWithEmailAndPassword(email, password)
    }

    override suspend fun signUpWithEmailAndPassword(email: String, password: String) = handleFlow {
        accuracyRepository.signUpWithEmailAndPassword(email, password)
    }

    override suspend fun isLogged(): Flow<Boolean> = handleFlow {
        accuracyRepository.isLogged()
    }

    override suspend fun logout(): Flow<Boolean> = handleFlow {
        accuracyRepository.logout()
    }
}