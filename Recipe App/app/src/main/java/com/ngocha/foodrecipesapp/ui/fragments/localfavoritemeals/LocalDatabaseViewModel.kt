package com.ngocha.foodrecipesapp.ui.fragments.localfavoritemeals

import android.util.Log
import androidx.lifecycle.*
import com.ngocha.foodrecipesapp.base.BaseViewModel
import com.ngocha.foodrecipesapp.constants.Constants
import com.ngocha.foodrecipesapp.data.pojo.Meal
import com.ngocha.foodrecipesapp.data.usecases.LocalMealUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocalDatabaseViewModel @Inject constructor(
    private val localMealUseCase: LocalMealUseCase,
) : BaseViewModel() {

    private var _mealsMutableLiveData = MutableLiveData<List<Meal>>()
    val mealLiveData: LiveData<List<Meal>> get() = _mealsMutableLiveData

    fun insertAndUpdateMealIntoDB(meal: Meal) {
        requestFlow {
            localMealUseCase.insertAndUpdateMealIntoDB(meal)
        }
    }

    fun deleteMealFromDB(meal: Meal) {
        requestFlow {
            localMealUseCase.deleteMealFromDB(meal)
        }
    }

    fun getAllMealsFromDB() {
        requestFlow {
            localMealUseCase.getAllMealsFromDB().collect {
                _mealsMutableLiveData.postValue(it)
            }
        }
    }
}