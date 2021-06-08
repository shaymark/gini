package com.markoapps.gini.models

import com.google.gson.annotations.SerializedName

data class NumberResponse (
    @SerializedName("numbers")val custNumbers: List<CustNumber>
        )

data class CustNumber (
    @SerializedName("number")val custNumber: Int
        )
