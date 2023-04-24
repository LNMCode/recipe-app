package com.ngocha.foodrecipesapp.ui.fragments.splash

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ngocha.foodrecipesapp.databinding.FragmentSplashBinding
import com.ngocha.foodrecipesapp.ui.activities.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : Fragment() {

    private lateinit var binding: FragmentSplashBinding

    private val splashViewModel: SplashViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashBinding.inflate(inflater, container, false);
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpEventSplash()
    }

    private fun setUpEventSplash() {
        splashViewModel.startSplashTimer(
            callBackNavigate = {
                findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToSignInFragment())
            },
            callBackActivity = {
                navToMainActivity()
            }
        )
    }

    private fun navToMainActivity() {
        val intent = Intent(context, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }
}