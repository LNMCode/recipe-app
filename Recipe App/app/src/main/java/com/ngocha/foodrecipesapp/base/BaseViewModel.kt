package com.ngocha.foodrecipesapp.base

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ngocha.foodrecipesapp.constants.Constants
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel() {

    var parentJob: Job? = null
        protected set

    var handlerException = CoroutineExceptionHandler { _, e -> handleException(e)}

    protected open fun handleException(e: Throwable) {
        Log.d(Constants.TAG, "${e.message}")
    }

    protected open fun requestFlow(block: suspend () -> Unit) {
        parentJob = viewModelScope.launch(handlerException) { block() }
        parentJob?.invokeOnCompletion {  }
    }

}