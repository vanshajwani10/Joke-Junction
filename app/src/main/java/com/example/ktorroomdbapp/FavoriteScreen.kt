package com.example.ktorroomdbapp

import android.annotation.SuppressLint
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
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun FavoriteScreenApp(navController: NavController) {

    val jokeDaoNew = MainApplication.jokeDatabase.getJokeDao()
    val coroutineScope = rememberCoroutineScope()
    val jokeList  = jokeDaoNew.getAllFav().observeAsState()

    Scaffold(topBar = { MyAppBar2(title ="Favorite Jokes" , navController = navController) },
        modifier = Modifier.fillMaxSize()) { innerPadding ->
        LazyColumn (modifier = Modifier
            .fillMaxWidth()
            .padding(innerPadding)
        ){
            items(jokeList.value?: emptyList()){
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
                                text =it.joke ,
                                modifier = Modifier
                                    .padding(16.dp),
                                color = Color.Black
                            )
                        }

                        IconButton(onClick = {
                            coroutineScope.launch(Dispatchers.IO) {
                                    jokeDaoNew.deleteFav(it.id)
                                    //isClicked=!isClicked
                            }
                        }) {
                            Icon(
                                    painter = painterResource(id = R.drawable.baseline_favorite_24),
                                    contentDescription = "fav"
                            )
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyAppBar2(title: String,navController: NavController) {
    TopAppBar(
        title = {
            Text(text = title)
        },
        navigationIcon = {
            IconButton(
                onClick = {
                    navController.popBackStack()
                }
            ) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
            }
        }
    )
}
