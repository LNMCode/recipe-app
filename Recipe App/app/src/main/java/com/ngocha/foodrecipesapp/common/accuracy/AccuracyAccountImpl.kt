package com.ngocha.foodrecipesapp.common.accuracy

import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class AccuracyAccountImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
) : AccuracyAccount{
    override fun signInWithEmailAndPassword(email: String, password: String) : AccuracyState {
        var state = AccuracyState.Result(false, null)
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnSuccessListener {
            state = AccuracyState.Result(true, null)
        }.addOnFailureListener {
            state = AccuracyState.Result(false, it.message)
        }
        return state
    }

    override fun signUpWithEmailAndPassword(email: String, password: String) : AccuracyState{
        var state = AccuracyState.Result(false, null)
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {
            state = AccuracyState.Result(true, null)
        }.addOnFailureListener {
            state = AccuracyState.Result(false, it.message)
        }
        return state
    }
}