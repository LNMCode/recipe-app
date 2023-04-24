package com.ngocha.foodrecipesapp.ui.fragments.home.adapter.mostpopularmeals

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ngocha.foodrecipesapp.common.imageloader.ImageLoader
import com.ngocha.foodrecipesapp.databinding.ItemOnPopularRecyclerViewBinding
import com.ngocha.foodrecipesapp.data.pojo.MealByCategory
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

class MostPopularMealsAdapter @Inject constructor(
    @ActivityContext private val context: Context,
    private val imageLoader: ImageLoader,
) :
    ListAdapter<MealByCategory, MostPopularMealsAdapter.ViewHolder>(MealDiffCallBack()) {

    var onItemViewClickListener: OnPopularMealsClickListener? = null

    fun setData(mealByCategory: List<MealByCategory>) {
        this.submitList(mealByCategory)
    }

    inner class ViewHolder(private val binding: ItemOnPopularRecyclerViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(mealByCategory: MealByCategory) {
            imageLoader.loadUrl(mealByCategory.strMealThumb, binding.imgPopularMeal)

            binding.cardViewContainerOnPopularMeals.setOnClickListener {
                onItemViewClickListener?.onPopularMealsClickListener(mealByCategory.idMeal)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemOnPopularRecyclerViewBinding.inflate(
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