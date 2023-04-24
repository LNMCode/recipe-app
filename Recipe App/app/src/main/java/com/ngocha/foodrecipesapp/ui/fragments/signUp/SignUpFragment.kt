package com.ngocha.foodrecipesapp.ui.fragments.signUp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ngocha.foodrecipesapp.databinding.FragmentSignUpBinding
import com.ngocha.foodrecipesapp.ui.activities.MainActivity
import com.ngocha.foodrecipesapp.ui.fragments.signIn.SignInFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignUpBinding

    private val signInViewModel: SignUpViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignUpBinding.inflate(inflater, container, false);
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpEventSignUp()
        setUpEventLoginAccount()
    }

    private fun setUpEventLoginAccount() {
        binding.loginAccount.setOnClickListener {
            findNavController().navigate(SignUpFragmentDirections.actionSignUpFragmentToSignInFragment())
        }
    }

    private fun setUpEventSignUp() {
        binding.btnSignIn.setOnClickListener { handleSignUp() }
    }

    private fun handleSignUp() {
        val email = binding.edEmail.text.toString()
        val password = binding.edPassword.text.toString()
        val passwordConfirm = binding.edPasswordConfirm.text.toString()
        signInViewModel.signUp(email, password, passwordConfirm)
        registerSignInObserver()
    }

    private fun registerSignInObserver() {
        signInViewModel.signUpStateMutableLiveData.observe(viewLifecycleOwner) {
            if (it is SignUpState.Result) {
                if (it.isDone) {
                    val intent = Intent(context, MainActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    startActivity(intent)
                } else {
                    Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}