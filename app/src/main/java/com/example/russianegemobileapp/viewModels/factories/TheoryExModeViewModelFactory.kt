package com.example.russianegemobileapp.viewModels.factories

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.russianegemobileapp.viewModels.TheoryExModeViewModel

class TheoryExModeViewModelFactory(val context: Context):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TheoryExModeViewModel(context) as T
    }
}