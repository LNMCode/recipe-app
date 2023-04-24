package com.ngocha.foodrecipesapp.common.accuracy

interface AccuracyAccount {
    fun signInWithEmailAndPassword(email: String, password: String) : AccuracyState

    fun signUpWithEmailAndPassword(email: String, password: String) : AccuracyState
}