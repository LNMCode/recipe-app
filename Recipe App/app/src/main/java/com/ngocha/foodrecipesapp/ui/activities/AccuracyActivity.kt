package com.ngocha.foodrecipesapp.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.ngocha.foodrecipesapp.R
import com.ngocha.foodrecipesapp.databinding.ActivityAccuracyBinding
import com.ngocha.foodrecipesapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AccuracyActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAccuracyBinding

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccuracyBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}