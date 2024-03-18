package com.example.graphqlpractice.UI

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.graphqlpractice.GetBillionaryQuery
import com.example.graphqlpractice.repository.GetBillionaryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GetBillionaryViewModel(private val repository: GetBillionaryRepository) : ViewModel() {

    private val _billionaries = MutableLiveData<List<GetBillionaryQuery.GetBillionary>?>()
    val billionaries: LiveData<List<GetBillionaryQuery.GetBillionary>?> = _billionaries

    fun getCountries() {
        viewModelScope.launch(Dispatchers.Main) {
            val data = repository.getBillionaries()
            if (data != null) {
                _billionaries.value = data
                Log.e("Data", "The List is not null: $data")
            } else {
                Log.e("Error", "The List is null")
            }
        }
    }
}
