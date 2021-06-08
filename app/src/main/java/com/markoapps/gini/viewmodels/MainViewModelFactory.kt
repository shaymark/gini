package com.markoapps.gini.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.markoapps.gini.network.IntApi

class MyViewModelFactory constructor(val intApi: IntApi)
    :  ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainViewModel::class.java)){
            return  MainViewModel(intApi) as T
        }

        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}