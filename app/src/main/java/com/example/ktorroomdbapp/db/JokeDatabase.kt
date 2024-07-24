package com.example.ktorroomdbapp.db

import androidx.room.Database
import androidx.room.RoomDatabase;
import com.example.ktorroomdbapp.model.JokeJSON

@Database(entities = [JokeJSON::class], version = 1)
abstract class JokeDatabase : RoomDatabase() {
    companion object{
        const val DATABASE_NAME = "joke_db"
    }
    abstract fun getJokeDao(): JokeDao
}
