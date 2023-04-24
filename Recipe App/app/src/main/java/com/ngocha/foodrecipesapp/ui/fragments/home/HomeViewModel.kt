package com.ngocha.foodrecipesapp.ui.fragments.home

import androidx.lifecycle.*
import com.ngocha.foodrecipesapp.base.BaseViewModel
import com.ngocha.foodrecipesapp.data.pojo.Category
import com.ngocha.foodrecipesapp.data.pojo.MealByCategory
import com.ngocha.foodrecipesapp.data.pojo.Meal
import com.ngocha.foodrecipesapp.data.usecases.RemoteMealUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val remoteMealUseCase: RemoteMealUseCase
) : BaseViewModel() {

    private var _mealRandomMutableLiveData = MutableLiveData<Meal>()
    val mealRandomLiveData: LiveData<Meal> get() = _mealRandomMutableLiveData

    private var _popularMealMutableLiveData = MutableLiveData<List<MealByCategory>>()
    val popularMealLiveData: LiveData<List<MealByCategory>> get() = _popularMealMutableLiveData

    private var _allCategoriesMutableLiveData = MutableLiveData<List<Category>>()
    val allCategoriesLiveData: LiveData<List<Category>> get() = _allCategoriesMutableLiveData

    fun getRandomMeal() {
        requestFlow {
            remoteMealUseCase.getRandomMeal().collect { mealList ->
                _mealRandomMutableLiveData.postValue(mealList!!.meals[0])
            }
        }
    }

    fun getPopularMealByCategory(categoryName: String) {
        requestFlow {
            remoteMealUseCase.getPopularMeals(categoryName).collect { mealList ->
                _popularMealMutableLiveData.postValue(mealList!!.meals)
            }
        }
    }

    fun getAllCategories() {
        requestFlow {
            remoteMealUseCase.getAllCategories().collect { mealList ->
                _allCategoriesMutableLiveData.postValue(mealList!!.categories)
            }
        }
    }
}