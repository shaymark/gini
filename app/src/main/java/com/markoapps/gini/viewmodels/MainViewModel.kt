package com.markoapps.gini.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.markoapps.gini.models.CustNumber

import com.markoapps.gini.network.IntApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.math.abs

enum class IntType{
    regular,
    coppled
}

data class IntItem(
    val type : IntType,
    val number: Int
)

data class MainState(
    val numbers: List<IntItem>,
    val two: Int
)

@HiltViewModel
class MainViewModel @Inject constructor(
    val intApi: IntApi)
    : ViewModel(){

    val mainSet: MutableLiveData<MainState> = MutableLiveData()

    init {
        refreshData()
        mainSet.value = MainState(listOf(), 0)
    }

    fun refreshData() {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                try {
                    val response = intApi.getInts()
                    val items = createNumbersList(response.custNumbers)
                    withContext(Dispatchers.Main) {
                        mainSet.value = mainSet.value!!.copy(
                            numbers = items
                        )
                    }
                } catch (e: Exception) {
                    Log.d(TAG, "refreshData: error: ${e}")
                }
            }
        }
    }

    private fun createNumbersList(numbers: List<CustNumber>): List<IntItem> {
        // calculate sums, if the value is 0 there are two complementary numbers
        val distintItems = numbers.map { it.custNumber }.distinct()
        val sumMap: MutableMap<Int, Int> = mutableMapOf()
        for(item in distintItems) {
            val key = abs(item)
            if(sumMap.containsKey(key)) {
                sumMap[key] = sumMap[key]!! + item
            } else {
                sumMap[key] = item
            }
        }

        return numbers.map { it.custNumber} .sorted().map {
            val key = abs(it)
            if(sumMap[key] == 0 && key != 0) {
                IntItem(IntType.coppled, it)
            } else {
                IntItem(IntType.regular, it)
            }
        }
    }

    companion object {
        val TAG = MainViewModel::class.java.simpleName
    }

}