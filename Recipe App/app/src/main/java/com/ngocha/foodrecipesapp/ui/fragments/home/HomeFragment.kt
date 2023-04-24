package com.ngocha.foodrecipesapp.ui.fragments.home

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ngocha.foodrecipesapp.common.imageloader.ImageLoader
import com.ngocha.foodrecipesapp.common.networkManager.NetworkManager
import com.ngocha.foodrecipesapp.databinding.FragmentHomeBinding
import com.ngocha.foodrecipesapp.ui.activities.AccuracyActivity
import com.ngocha.foodrecipesapp.ui.fragments.home.adapter.categories.CategoriesAdapter
import com.ngocha.foodrecipesapp.ui.fragments.home.adapter.categories.OnCategoryClickListener
import com.ngocha.foodrecipesapp.ui.fragments.home.adapter.mostpopularmeals.MostPopularMealsAdapter
import com.ngocha.foodrecipesapp.ui.fragments.home.adapter.mostpopularmeals.OnPopularMealsClickListener
import dagger.hilt.android.AndroidEntryPoint
import jp.wasabeef.recyclerview.animators.LandingAnimator
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@AndroidEntryPoint
class HomeFragment : Fragment(), OnPopularMealsClickListener, OnCategoryClickListener {

    private lateinit var binding: FragmentHomeBinding

    private val homeViewModel: HomeViewModel by viewModels()

    @Inject
    lateinit var mostPopularMealsAdapter: MostPopularMealsAdapter

    @Inject
    lateinit var categoriesAdapter: CategoriesAdapter

    @Inject
    lateinit var imageLoader: ImageLoader

    @Inject
    lateinit var networkManager: NetworkManager

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        checkingIsOnline()
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getRandomMeal()
        observeAndShowRandomMealInUi()

        setupPopularMealsRecyclerView()

        getPopularMeals()
        popularMealsObserver()

        setupCategoriesRecyclerView()
        homeViewModel.getAllCategories()
        categoriesObserver()
        logout()

        binding.btnSearch.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToSearchFragment2())
        }

        viewLifecycleOwner.lifecycleScope.launch {
            networkManager.isOnline().collectLatest { isOnline ->
                if (!isOnline) {
                    binding.progressBar.visibility = View.GONE
                    binding.imgNoInternet.visibility = View.VISIBLE
                }
            }
        }

        binding.swipeRefreshLayout.setOnRefreshListener {
            Handler(Looper.getMainLooper()).postDelayed({
                requireActivity().recreate()
                binding.swipeRefreshLayout.isRefreshing = false
            }, 300)
        }
    }

    override fun onStart() {
        super.onStart()
        binding.swipeRefreshLayout.isRefreshing = false
    }

    private fun getRandomMeal() {
        binding.progressBar.visibility = View.VISIBLE
        homeViewModel.getRandomMeal()
    }

    private fun observeAndShowRandomMealInUi() {
        homeViewModel.mealRandomLiveData.observe(viewLifecycleOwner) {
            if (it != null) {
                it.strMealThumb?.let { it1 -> imageLoader.loadUrl(it1, binding.imgRandomMeal) }
                binding.progressBar.visibility = View.GONE

                onRandomMealClickListener(it.idMeal)
            }
        }
    }

    private fun onRandomMealClickListener(id: String) {
        binding.imgRandomMeal.setOnClickListener {
            goToMealFragment(id)
        }
    }

    private fun setupPopularMealsRecyclerView() {
        binding.recViewMealsPopular.adapter = mostPopularMealsAdapter
        binding.recViewMealsPopular.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

        binding.recViewMealsPopular.itemAnimator = LandingAnimator().apply {
            addDuration = 200
        }
        mostPopularMealsAdapter.onItemViewClickListener = this
    }

    private fun getPopularMeals() {
        val categoriesNames = arrayOf(
            "Beef", "Breakfast", "Chicken", "Dessert", "Goat", "Lamb",
            "Miscellaneous", "Pasta", "Pork", "Seafood", "Side", "Starter", "Vegan", "Vegetarian"
        )
        val randomCategory = categoriesNames[Random.nextInt(categoriesNames.size)]
        homeViewModel.getPopularMealByCategory(randomCategory)
    }

    private fun popularMealsObserver() {
        homeViewModel.popularMealLiveData.observe(viewLifecycleOwner) {
            if (it != null) {
                mostPopularMealsAdapter.setData(it)
            }
        }
    }

    override fun onPopularMealsClickListener(mealId: String) {
        goToMealFragment(mealId)
    }

    private fun goToMealFragment(id: String) {
        val action = HomeFragmentDirections.actionHomeFragmentToMealFragment(id)
        findNavController().navigate(action)
    }

    private fun setupCategoriesRecyclerView() {
        binding.recViewCategories.adapter = categoriesAdapter
        binding.recViewCategories.layoutManager =
            GridLayoutManager(activity, 3, GridLayoutManager.VERTICAL, false)

        binding.recViewCategories.itemAnimator = LandingAnimator().apply {
            addDuration = 200
        }
        categoriesAdapter.onItemClickListener = this
    }

    private fun categoriesObserver() {
        homeViewModel.allCategoriesLiveData.observe(viewLifecycleOwner) {
            if (it != null) {
                categoriesAdapter.setData(it)
            }
        }
    }

    override fun onCategoryClickListener(categoryName: String) {
        goToMealsByCategoriesFragment(categoryName)
    }

    private fun goToMealsByCategoriesFragment(categoryName: String) {
        val action =
            HomeFragmentDirections.actionHomeFragmentToMealsByCategoriesFragment(categoryName)
        findNavController().navigate(action)
    }

    private fun checkingIsOnline() {
        viewLifecycleOwner.lifecycleScope.launch {
            networkManager.isOnline().collectLatest { isOnline ->
                if (!isOnline) {
                    Toast.makeText(requireContext(), "No Internet", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun logout() {
        binding.logout.setOnClickListener {
            homeViewModel.logout {
                navToSignInActivity()
            }
        }
    }

    private fun navToSignInActivity() {
        val intent = Intent(context, AccuracyActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }
}