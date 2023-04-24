package com.ngocha.foodrecipesapp.ui.fragments.signIn

sealed class SignInState {
    data class Result(val isDone: Boolean, val message: String?) : SignInState()
}