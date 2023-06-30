package com.example.radius.repo

import Property
import com.example.radius.networkcalls.NetworkState
import com.example.radius.networkcalls.RetrofitService


class FacilityRepository constructor(private val retrofitService: RetrofitService) {

    suspend fun getAllProperties() : NetworkState<Property> {
        val response = retrofitService.getAllProperties()
        return if (response.isSuccessful) {
            val responseBody = response.body()
            if (responseBody != null) {
                NetworkState.Success(responseBody)
            } else {
                NetworkState.Error(response)
            }
        } else {
            NetworkState.Error(response)
        }
    }

}