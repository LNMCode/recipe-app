package com.ngocha.foodrecipesapp.ui.fragments.signIn

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ngocha.foodrecipesapp.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class SignInViewModel: BaseViewModel() {

    private var _signInStateMutableLiveData = MutableLiveData<SignInState>()
    val signInStateMutableLiveData: LiveData<SignInState> get() = _signInStateMutableLiveData

    fun login(email: String?, password: String?) {
        val isCorrect = checkEmailAndPassword(email, password)
        if (!isCorrect) return

    }

    private fun checkEmailAndPassword(email: String?, password: String?) : Boolean {
        val isEmptyEmail = checkNullOrBlankValue(email)
        if (isEmptyEmail) {
            _signInStateMutableLiveData.postValue(SignInState.Failed("Please enter email"))
            return false
        }
        val isEmptyPassword = checkNullOrBlankValue(password)
        if (isEmptyPassword) {
            _signInStateMutableLiveData.postValue(SignInState.Failed("Please enter password"))
            return false
        }
        return true
    }

    private fun checkNullOrBlankValue(value: String?) : Boolean {
        return value.isNullOrBlank()
    }

}