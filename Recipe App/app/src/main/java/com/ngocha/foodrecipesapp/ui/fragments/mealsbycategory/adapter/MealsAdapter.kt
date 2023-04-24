package com.ngocha.foodrecipesapp.ui.fragments.mealsbycategory.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ngocha.foodrecipesapp.common.imageloader.ImageLoader
import com.ngocha.foodrecipesapp.databinding.ItemOnRecyclerviewInFragmentMealsByCategoriesBinding
import com.ngocha.foodrecipesapp.data.pojo.MealByCategory
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

class MealsAdapter @Inject constructor(
    @ActivityContext private val context: Context,
    private val imageLoader: ImageLoader
) :
    ListAdapter<MealByCategory, MealsAdapter.ViewHolder>(MealDiffCallBack()) {

    var onMealClickListener: OnMealClickListener? = null

    fun setData(mealByCategoryList: List<MealByCategory>) {
        this.submitList(mealByCategoryList)
    }

    inner class ViewHolder(private val binding: ItemOnRecyclerviewInFragmentMealsByCategoriesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(mealByCategory: MealByCategory) {
            imageLoader.loadUrl(mealByCategory.strMealThumb, binding.imgMeal)

            binding.tvMealName.text = mealByCategory.strMeal

            binding.cardViewContainer.setOnClickListener {
                onMealClickListener?.onMealClickListener(mealByCategory)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemOnRecyclerviewInFragmentMealsByCategoriesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

}

class MealDiffCallBack : DiffUtil.ItemCallback<MealByCategory>() {

    override fun areItemsTheSame(oldItem: MealByCategory, newItem: MealByCategory): Boolean {
        return oldItem.idMeal == newItem.idMeal
    }

    override fun areContentsTheSame(oldItem: MealByCategory, newItem: MealByCategory): Boolean {
        return oldItem == newItem
    }

}