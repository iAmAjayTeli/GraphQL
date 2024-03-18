package com.example.graphqlpractice.UI

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.graphqlpractice.GetBillionaryQuery
import com.example.graphqlpractice.R
import com.example.graphqlpractice.adapter.GetBillionariesAdapter
import com.example.graphqlpractice.databinding.ActivityMainBinding
import com.example.graphqlpractice.repository.GetBillionaryRepository

class MainActivity : AppCompatActivity() {
    private lateinit var getBillionaryViewModel: GetBillionaryViewModel
    private lateinit var billionarAdapter: GetBillionariesAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initialize()
    }

    private fun initialize() {
        val repository = GetBillionaryRepository()
        val getBillionaryViewModelFactory = GetBillionaryViewModelFactory(repository)
        getBillionaryViewModel = ViewModelProvider(this, getBillionaryViewModelFactory)
            .get(GetBillionaryViewModel::class.java)

        getBillionaryViewModel.billionaries.observe(this, Observer { billionaries ->
            billionaries?.let {
                updateRecyclerView(it)
            } ?: run {
                Toast.makeText(this, "The list is empty", Toast.LENGTH_SHORT).show()
            }
        })

        getBillionaryViewModel.getCountries()
    }

    private fun updateRecyclerView(billionaries: List<GetBillionaryQuery.GetBillionary>) {
        billionarAdapter = GetBillionariesAdapter()
        billionarAdapter.differ.submitList(billionaries)
        binding.recyclerView.apply {
            adapter = billionarAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }
}