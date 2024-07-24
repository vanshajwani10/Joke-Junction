package com.example.ktorroomdbapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Entity(tableName = "Jokes",indices = [Index(value = ["joke"], unique = true)])
@Serializable
data class JokeJSON(
    @PrimaryKey(autoGenerate = true)
    val identity : Int? = null,
    @SerialName("category")
    val category: String,
    @SerialName("type")
    val type: String,
    @ColumnInfo @SerialName("joke")
    val joke: String,
    @SerialName("id")
    val id: Int,
    @SerialName("safe")
    val safe: Boolean,
    @SerialName("lang")
    val lang: String
)
