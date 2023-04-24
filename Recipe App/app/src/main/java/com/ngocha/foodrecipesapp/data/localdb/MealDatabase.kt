package com.ngocha.foodrecipesapp.data.localdb

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ngocha.foodrecipesapp.data.pojo.Meal

@Database(entities = [Meal::class], version = 1, exportSchema = false)
@TypeConverters(MealTypeConverter::class)
abstract class MealDatabase : RoomDatabase() {
    abstract fun mealDao(): MealDao
}