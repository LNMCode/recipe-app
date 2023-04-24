package com.ngocha.foodrecipesapp.common.imageloader

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.RequestManager
import javax.inject.Inject

class ImageLoaderImpl
@Inject constructor(
    private val glide: RequestManager
) : ImageLoader {
    override fun loadUrl(url: String, view: ImageView) {
        glide.load(url).into(view)
    }
}