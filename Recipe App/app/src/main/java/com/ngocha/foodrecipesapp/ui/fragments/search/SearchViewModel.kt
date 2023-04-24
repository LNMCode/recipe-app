package com.ngocha.foodrecipesapp.ui.fragments.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ngocha.foodrecipesapp.base.BaseViewModel
import com.ngocha.foodrecipesapp.data.pojo.Meal
import com.ngocha.foodrecipesapp.data.usecases.accuracy.AccuracyUseCase
import com.ngocha.foodrecipesapp.data.usecases.remoteMeal.RemoteMealUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val remoteMealUseCase: RemoteMealUseCase
) : BaseViewModel() {

    private var _mealsNameMutableLiveData = MutableLiveData<List<Meal>>()
    val mealsNameMutableLiveData: LiveData<List<Meal>> get() = _mealsNameMutableLiveData

    fun getMealByName(name: String) {
        requestFlow {
            remoteMealUseCase.getMealByName(name).collect {
                _mealsNameMutableLiveData.postValue(it.meals)
            }
        }
    }
}