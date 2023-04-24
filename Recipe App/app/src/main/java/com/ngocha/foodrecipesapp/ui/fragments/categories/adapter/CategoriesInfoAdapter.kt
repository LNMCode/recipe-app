package com.ngocha.foodrecipesapp.ui.fragments.categories.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ngocha.foodrecipesapp.common.imageloader.ImageLoader
import com.ngocha.foodrecipesapp.databinding.ItemOnRecOnCategoriesInfoFrgBinding
import com.ngocha.foodrecipesapp.data.pojo.Category
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class CategoriesInfoAdapter @Inject constructor(
    @ActivityContext private val context: Context,
    private val imageLoader: ImageLoader,
) :
    ListAdapter<Category, CategoriesInfoAdapter.ViewHolder>(CategoriesInfoDiffCallBack()) {

    fun setData(categories: List<Category>) {
        this.submitList(categories)
    }

    inner class ViewHolder(private val binding: ItemOnRecOnCategoriesInfoFrgBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(category: Category) {
            imageLoader.loadUrl(category.strCategoryThumb, binding.imgCategory)
            binding.tvCategoryName.text = category.strCategory
            binding.tvMealDec.text = category.strCategoryDescription
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemOnRecOnCategoriesInfoFrgBinding.inflate(
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

class CategoriesInfoDiffCallBack : DiffUtil.ItemCallback<Category>() {

    override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem.idCategory == newItem.idCategory
    }

    override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem == newItem
    }

}