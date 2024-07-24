package com.example.ktorroomdbapp

import android.app.Application
import androidx.room.Room
import com.example.ktorroomdbapp.db.JokeDatabase

class MainApplication : Application() {
    companion object{
        lateinit var jokeDatabase:JokeDatabase
    }

    override fun onCreate() {
        super.onCreate()
       jokeDatabase =  Room.databaseBuilder(applicationContext,
            JokeDatabase::class.java,
            JokeDatabase.DATABASE_NAME).build()
    }
}
