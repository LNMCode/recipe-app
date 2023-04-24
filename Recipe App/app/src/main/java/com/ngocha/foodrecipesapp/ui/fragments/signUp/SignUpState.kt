package com.ngocha.foodrecipesapp.ui.fragments.signUp

sealed class SignUpState {
    data class Result(val isDone: Boolean, val message: String?) : SignUpState()
}