package com.example.ktorroomdbapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ktorroomdbapp.model.AmountJSON
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch


class MainViewModel : ViewModel() {

    private val apiClient = APIService(httpClient)

    private val _items: MutableStateFlow<AmountJSON?> = MutableStateFlow(null)

    val items: Flow<AmountJSON?> = _items

    private fun loadItems() = viewModelScope.launch {
        val data = apiClient.getJokes()
        _items.emit(data)
    }

    init {
        loadItems()
    }
}


