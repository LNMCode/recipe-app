package com.ngocha.foodrecipesapp.common.imageloader

import android.widget.ImageView

interface ImageLoader {
    fun loadUrl(url: String, view: ImageView)
}