package com.inspiringteam.ibm_airtouch.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ExchangeRate(@Expose @SerializedName("from") val from: String,
                       @Expose @SerializedName("to") val to: String,
                       @Expose @SerializedName("rate") var rate: String)