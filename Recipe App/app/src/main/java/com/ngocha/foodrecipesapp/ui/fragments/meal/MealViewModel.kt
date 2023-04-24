package com.ngocha.foodrecipesapp.ui.fragments.meal

import androidx.lifecycle.*
import com.ngocha.foodrecipesapp.base.BaseViewModel
import com.ngocha.foodrecipesapp.data.pojo.Meal
import com.ngocha.foodrecipesapp.data.usecases.RemoteMealUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MealViewModel @Inject constructor(
    private val remoteMealUseCase: RemoteMealUseCase,
) : BaseViewModel() {

    private var _mealDetailsMutableLiveData = MutableLiveData<Meal>()
    val mealDetailsLiveData: LiveData<Meal> get() = _mealDetailsMutableLiveData

    fun getMealDetailsById(id: String) {
        requestFlow {
            remoteMealUseCase.getMealDetailsById(id).collect {
                _mealDetailsMutableLiveData.postValue(it.meals[0])
            }
        }
    }
}