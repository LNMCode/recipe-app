package com.ngocha.foodrecipesapp.ui.fragments.search

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ngocha.foodrecipesapp.databinding.FragmentSearchBinding
import com.ngocha.foodrecipesapp.ui.fragments.home.HomeFragmentDirections
import com.ngocha.foodrecipesapp.ui.fragments.search.adapter.OnSearchClickListener
import com.ngocha.foodrecipesapp.ui.fragments.search.adapter.SearchAdapter
import dagger.hilt.android.AndroidEntryPoint
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator
import javax.inject.Inject

@AndroidEntryPoint
class SearchFragment : Fragment(), OnSearchClickListener {

    private lateinit var binding: FragmentSearchBinding

    private val signInViewModel: SearchViewModel by viewModels()

    @Inject
    lateinit var searchAdapter: SearchAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false);
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setUpEventSearch()
        setBtnBack()
    }

    private fun setBtnBack() {
        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerViewSearchBoarding.adapter = searchAdapter
        binding.recyclerViewSearchBoarding.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

        binding.recyclerViewSearchBoarding.itemAnimator = SlideInUpAnimator().apply {
            addDuration = 200
        }

        searchAdapter.onItemClickListener = this
    }

    private fun setUpEventSearch() {
        binding.btnSearch.setOnClickListener { handleSearch() }
    }

    private fun handleSearch() {
        val value = binding.etSearch.text.toString()
        signInViewModel.getMealByName(value)
        registerSearchObserver()
    }

    private fun registerSearchObserver() {
        signInViewModel.mealsNameMutableLiveData.observe(viewLifecycleOwner) {
            searchAdapter.setData(it)
        }
    }

    override fun onSearchClickListener(idMeal: String) {
        val action = SearchFragmentDirections.actionSearchFragmentToMealFragment(idMeal)
        findNavController().navigate(action)
    }
}