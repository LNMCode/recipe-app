package com.ngocha.foodrecipesapp.constants

import com.ngocha.foodrecipesapp.BuildConfig

interface Constants {
    companion object {
        const val TAG = "myTag"
        const val MEAL_DATABASE_NAME = "meal_db"
        const val MEAL_API_BASE_URL = BuildConfig.MEAL_API_BASE_URL
    }
}