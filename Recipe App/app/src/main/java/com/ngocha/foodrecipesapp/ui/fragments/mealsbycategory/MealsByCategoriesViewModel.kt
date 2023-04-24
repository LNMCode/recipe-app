package com.ngocha.foodrecipesapp.ui.fragments.mealsbycategory

import androidx.lifecycle.*
import com.ngocha.foodrecipesapp.base.BaseViewModel
import com.ngocha.foodrecipesapp.data.pojo.MealByCategory
import com.ngocha.foodrecipesapp.data.usecases.RemoteMealUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MealsByCategoriesViewModel @Inject constructor(
    private val remoteMealUseCase: RemoteMealUseCase,
) : BaseViewModel() {

    private var _mealsByCategoriesMutableLiveData = MutableLiveData<List<MealByCategory>>()
    val mealsByCategoriesLiveData: LiveData<List<MealByCategory>> get() = _mealsByCategoriesMutableLiveData

    fun getMealsByCategories(CategoryName: String) {
        requestFlow {
            remoteMealUseCase.getMealsByCategory(CategoryName).collect {
                _mealsByCategoriesMutableLiveData.postValue(it!!.meals)
            }
        }
    }
}