package com.example.ktorroomdbapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ktorroomdbapp.model.JokeJSON

@Dao
interface JokeDao {

    @Query("SELECT * FROM Jokes")
    fun getAllFav(): LiveData<List<JokeJSON>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertFav(joke: JokeJSON)


    @Query("DELETE FROM Jokes where id= :id")
    fun deleteFav(id: Int)
}


