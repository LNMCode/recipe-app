package com.ngocha.foodrecipesapp.ui.fragments.signIn

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ngocha.foodrecipesapp.base.BaseViewModel
import com.ngocha.foodrecipesapp.data.usecases.accuracy.AccuracyUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val accuracyUseCase: AccuracyUseCase,
) : BaseViewModel() {

    private var _signInStateMutableLiveData = MutableLiveData<SignInState>()
    val signInStateMutableLiveData: LiveData<SignInState> get() = _signInStateMutableLiveData

    fun signIn(email: String?, password: String?) {
        val isCorrect = checkEmailAndPassword(email, password)
        if (!isCorrect) return
        requestFlow {
            accuracyUseCase.signInWithEmailAndPassword(email!!, password!!).collect {
                _signInStateMutableLiveData.postValue(it)
            }
        }
    }

    private fun checkEmailAndPassword(email: String?, password: String?): Boolean {
        val isEmptyEmail = checkNullOrBlankValue(email)
        if (isEmptyEmail) {
            _signInStateMutableLiveData.postValue(
                SignInState.Result(
                    false,
                    "Please enter email"
                )
            )
            return false
        }
        val isEmptyPassword = checkNullOrBlankValue(password)
        if (isEmptyPassword) {
            _signInStateMutableLiveData.postValue(
                SignInState.Result(
                    false,
                    "Please enter password"
                )
            )
            return false
        }
        return true
    }

    private fun checkNullOrBlankValue(value: String?): Boolean {
        return value.isNullOrBlank()
    }

}