package com.example.raya_edu.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
/*
class SignInViewModel(private val repository: AuthRepository) : ViewModel() {
    private val _signInState = MutableLiveData<Result<String>>()
    val signInState: LiveData<Result<String>> = _signInState

    fun signIn(userCode: String, id: String) {
        viewModelScope.launch {
            _signInState.value = repository.signIn(userCode, id)
        }
    }

    fun isSignIn(): Boolean = repository.isSignIn()
}
*/