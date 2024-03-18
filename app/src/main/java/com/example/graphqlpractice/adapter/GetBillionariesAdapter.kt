package com.example.graphqlpractice.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ListAdapter
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.graphqlpractice.GetBillionaryQuery
import com.example.graphqlpractice.R
import com.example.graphqlpractice.databinding.ItemLayoutBinding

class GetBillionariesAdapter() :RecyclerView.Adapter<GetBillionariesAdapter.ViewHolder>()
{
    class ViewHolder(val binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root){

    }

    private val differCallBack= object : DiffUtil.ItemCallback<GetBillionaryQuery.GetBillionary>(){
        override fun areItemsTheSame(
            oldItem: GetBillionaryQuery.GetBillionary,
            newItem: GetBillionaryQuery.GetBillionary
        ): Boolean {
           return oldItem.name==newItem.name
        }

        override fun areContentsTheSame(
            oldItem: GetBillionaryQuery.GetBillionary,
            newItem: GetBillionaryQuery.GetBillionary
        ): Boolean {
            return oldItem==newItem
        }


    }

    val differ= AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    }



    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentData= differ.currentList[position]

        holder.binding.billionary= currentData

    }
}