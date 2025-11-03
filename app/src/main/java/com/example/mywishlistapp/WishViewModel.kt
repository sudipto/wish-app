package com.example.mywishlistapp

import androidx.compose.foundation.text.input.TextFieldState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mywishlistapp.data.Wish
import com.example.mywishlistapp.data.WishRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class WishViewModel(
    val repository: WishRepository = Graph.repository
): ViewModel() {
    val wishTitleState = TextFieldState("")
    val wishDescriptionState = TextFieldState("")

    lateinit var getAllWishes: Flow<List<Wish>>

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getAllWishes = repository.getWishes()
        }
    }

    fun addWish(wish: Wish) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addAWish(wish)
        }
    }

    fun getWishById(id: Long): Flow<Wish> {
        return repository.getWishById(id)
    }

    fun updateWish(wish: Wish) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateWish(wish)
        }
    }

    fun deleteWish(wish: Wish) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteWish(wish)
        }
    }
}