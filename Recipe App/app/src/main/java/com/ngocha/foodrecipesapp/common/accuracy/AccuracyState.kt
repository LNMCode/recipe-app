package com.ngocha.foodrecipesapp.common.accuracy

sealed class AccuracyState {
    class Result(isDone: Boolean, message: String?) : AccuracyState()
}