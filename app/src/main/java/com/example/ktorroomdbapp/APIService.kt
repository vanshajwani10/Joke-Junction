package com.example.ktorroomdbapp

import com.example.ktorroomdbapp.model.AmountJSON
import com.example.ktorroomdbapp.model.JokeJSON
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class APIService(private val client: HttpClient) {

    private val baseUrl = "https://v2.jokeapi.dev/joke/Any?type=single&amount=10"
    suspend fun getJokes() = client.get(baseUrl).body<AmountJSON>()

}

