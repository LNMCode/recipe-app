package com.ngocha.foodrecipesapp.ui.fragments.localfavoritemeals

import androidx.lifecycle.*
import com.ngocha.foodrecipesapp.base.BaseViewModel
import com.ngocha.foodrecipesapp.data.pojo.Meal
import com.ngocha.foodrecipesapp.data.usecases.favorite.FavoriteUseCase
import com.ngocha.foodrecipesapp.data.usecases.localMeal.LocalMealUseCase
import com.ngocha.foodrecipesapp.data.usecases.remoteMeal.RemoteMealUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class LocalDatabaseViewModel @Inject constructor(
    private val localMealUseCase: LocalMealUseCase,
    private val remoteMealUseCase: RemoteMealUseCase,
    private val favoriteUseCase: FavoriteUseCase,
) : BaseViewModel() {

    private var _mealsMutableLiveData = MutableLiveData<List<Meal>>()
    val mealLiveData: LiveData<List<Meal>> get() = _mealsMutableLiveData

    private var _mealsGetByIdLiveDta : Meal? = null

    fun insertAndUpdateMealIntoDB(meal: Meal) {
        requestFlow {
            localMealUseCase.insertAndUpdateMealIntoDB(meal)
            favoriteUseCase.saveFavoriteMealToFireStore(meal.idMeal)
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

    fun getAllMealsIdFromFireStore() {
        requestFlow {
            favoriteUseCase.getAllFavoriteFromFireStore().collect {
                for (id in it) {
                    remoteMealUseCase.getMealDetailsById(id).collect { list ->
                        if (!mealLiveData.value?.contains(list.meals[0])!!) {
                            insertAndUpdateMealIntoDB(list.meals[0])
                        }
                    }
                }
                getAllMealsFromDB()
            }
        }
    }
}