package com.ngocha.foodrecipesapp.ui.fragments.splash

import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.ngocha.foodrecipesapp.base.BaseViewModel
import com.ngocha.foodrecipesapp.data.usecases.accuracy.AccuracyUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val accuracyUseCase: AccuracyUseCase,
) : BaseViewModel() {

    fun startSplashTimer(callBackNavigate: () -> Unit, callBackActivity: () -> Unit) {
        viewModelScope.launch {
            delay(2000)
            checkLogged(callBackNavigate, callBackActivity)
        }
    }

    private fun checkLogged(callBackNavigate: () -> Unit, callBackActivity: () -> Unit) {
        requestFlow {
            accuracyUseCase.isLogged().collect{
                if (it) {
                    callBackActivity()
                } else {
                    callBackNavigate()
                }
            }
        }
    }
}