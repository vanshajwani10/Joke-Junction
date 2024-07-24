package com.example.ktorroomdbapp.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class AmountJSON(
    @SerialName("error")
    val error: Boolean,
    @SerialName("amount")
    val amount: String,
    @SerialName("jokes")
    val jokes: List<JokeJSON>
)
