package com.example.graphqlpractice.UI

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.graphqlpractice.repository.GetBillionaryRepository

class GetBillionaryViewModelFactory(private val repository: GetBillionaryRepository) :ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
       return GetBillionaryViewModel(repository) as T
    }
}