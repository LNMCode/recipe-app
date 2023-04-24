package com.ngocha.foodrecipesapp.common.networkManager

import kotlinx.coroutines.flow.Flow

interface NetworkManager {
    suspend fun isOnline(): Flow<Boolean>
}