package com.ngocha.foodrecipesapp.ui.fragments.favorite.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ngocha.foodrecipesapp.common.imageloader.ImageLoader
import com.ngocha.foodrecipesapp.databinding.ItemOnRecOnFavoriteFrgBinding
import com.ngocha.foodrecipesapp.data.pojo.Meal
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

class FavoriteMealsAdapter @Inject constructor(
    @ActivityContext private val context: Context,
    private val imageLoader: ImageLoader,
) :
    ListAdapter<Meal, FavoriteMealsAdapter.ViewHolder>(FavoriteMealsDiffCallBack()) {

    var onMealClickListener: OnFavoriteMealClickListener? = null

    fun setData(meals: List<Meal>) {
        this.submitList(meals)
    }

    inner class ViewHolder(private val binding: ItemOnRecOnFavoriteFrgBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(meal: Meal) {
            meal.strMealThumb?.let { imageLoader.loadUrl(it, binding.imgMeal) }

            binding.tvMealName.text = meal.strMeal

            binding.cardViewContainer.setOnClickListener {
                onMealClickListener?.onFavoriteMealClickListener(meal)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemOnRecOnFavoriteFrgBinding.inflate(
                LayoutInflater.from(context),
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


class FavoriteMealsDiffCallBack : DiffUtil.ItemCallback<Meal>() {

    override fun areItemsTheSame(oldItem: Meal, newItem: Meal): Boolean {
        return oldItem.idMeal == newItem.idMeal
    }

    override fun areContentsTheSame(oldItem: Meal, newItem: Meal): Boolean {
        return oldItem == newItem
    }

}