package com.example.ktorroomdbapp

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun StartScreen(viewModel: MainViewModel,navController: NavController) {

    val coroutineScope = rememberCoroutineScope()
    val jokeItem = viewModel.items.collectAsState(initial = null)
    val jokeList = jokeItem.value?.jokes
    val jokesDao = MainApplication.jokeDatabase.getJokeDao()
    Scaffold(
        topBar = { MyAppBar(title = "Jokes Junction", navController) },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
        ) {
            items(items = jokeList ?: emptyList()) {
                var isClicked by rememberSaveable { mutableStateOf(false) }
                Box(
                    modifier = Modifier
                        .background(Color.Cyan)
                        .border(BorderStroke(1.dp, Color.White))
                )
                {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .padding(8.dp)
                                .clip(RoundedCornerShape(48.dp))
                        ) {
                            Text(
                                text = it.joke,
                                modifier = Modifier
                                    .padding(16.dp),
                                color = Color.Black
                            )
                        }

                        IconButton(onClick = {
                            val clicked = !isClicked
                            isClicked = clicked
                           coroutineScope.launch(Dispatchers.IO) {
                                if (!clicked) {
                                    jokesDao.deleteFav(it.id)
                                } else {
                                    try {
                                        jokesDao.insertFav(it)
                                    } catch (_: Exception) {
                                    }
                                }
                            }
                        }) {
                            if (isClicked) {
                                Icon(
                                    painter = painterResource(id = R.drawable.baseline_favorite_24),
                                    contentDescription = "fav"
                                )
                            } else {
                                Icon(
                                    painter = painterResource(id = R.drawable.baseline_favorite_border_24),
                                    contentDescription = "fav"
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyAppBar(title: String,navController: NavController) {
    TopAppBar(
        title = {
            Text(text = title)
        },
        actions = {
            Button(onClick = {
                navController.navigate("FavoriteScreen")
            }) {
                Text(text = "Favorites")
            }
        }
    )
}

