package com.example.graphqlpractice.repository

import android.util.Log
import com.apollographql.apollo3.ApolloClient
import com.example.graphqlpractice.GetBillionaryQuery
import com.example.graphqlpractice.Model.SimpleCountry

class GetBillionaryRepository{

    private val apolloClient = ApolloClient.builder()
        .serverUrl("https://tricky-tips-search.loca.lt")
        .build()

    suspend fun getBillionaries(): List<GetBillionaryQuery.GetBillionary>? {
        val response = try {
            apolloClient.query(GetBillionaryQuery()).execute()
        } catch (e: Exception) {
           Log.e("RepositoryError", "${e.message}")
            null
        }

        return response?.data?.getBillionaries
    }
}

