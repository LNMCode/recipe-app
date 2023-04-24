package com.ngocha.foodrecipesapp.ui.fragments.signIn

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ngocha.foodrecipesapp.databinding.FragmentSignInBinding
import com.ngocha.foodrecipesapp.ui.activities.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInFragment : Fragment() {

    private lateinit var binding: FragmentSignInBinding

    private val signInViewModel: SignInViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignInBinding.inflate(inflater, container, false);
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpEventSignIn()
        setUpEventCreateAccount()
    }

    private fun setUpEventCreateAccount() {
        binding.createAccount.setOnClickListener {
            findNavController().navigate(SignInFragmentDirections.actionSignInFragmentToSignUpFragment())
        }
    }

    private fun setUpEventSignIn() {
        binding.btnSignIn.setOnClickListener { handleSignIn() }
    }

    private fun handleSignIn() {
        val email = binding.edEmail.text.toString()
        val password = binding.edPassword.text.toString()
        signInViewModel.signIn(email, password)
        registerSignInObserver()
    }

    private fun registerSignInObserver() {
        signInViewModel.signInStateMutableLiveData.observe(viewLifecycleOwner) {
            Log.d("####", it.toString())
            if (it is SignInState.Result) {
                if (it.isDone) {
                    navToMainActivity()
                } else {
                    Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun navToMainActivity() {
        val intent = Intent(context, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

}