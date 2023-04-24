package com.ngocha.foodrecipesapp.ui.fragments.signIn

sealed class SignInState {
    class Failed(message: String) : SignInState()

    object Done : SignInState()
}