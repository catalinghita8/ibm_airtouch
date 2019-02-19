package com.inspiringteam.ibm_airtouch.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Transaction(
    @Expose @SerializedName("sku") val productName: String,
    @Expose @SerializedName("amount") val amount: String,
    @Expose @SerializedName("currency") var currency: String
)
