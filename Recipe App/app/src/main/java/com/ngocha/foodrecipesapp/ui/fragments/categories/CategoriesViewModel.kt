package com.ngocha.foodrecipesapp.ui.fragments.categories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ngocha.foodrecipesapp.base.BaseViewModel
import com.ngocha.foodrecipesapp.data.pojo.Category
import com.ngocha.foodrecipesapp.data.usecases.RemoteMealUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val remoteUserCase: RemoteMealUseCase
) : BaseViewModel() {

    private var _categoriesMutableLiveData = MutableLiveData<List<Category>>()
    val categoriseLiveData: LiveData<List<Category>> get() = _categoriesMutableLiveData

    fun fetchAllCategories() {
        requestFlow {
            remoteUserCase.getAllCategories().collect {categoryList ->
                _categoriesMutableLiveData.postValue(categoryList!!.categories)
            }
        }
    }
}